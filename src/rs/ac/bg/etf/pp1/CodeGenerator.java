package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private static final int DEFAULT_INT_PRINT_WIDTH = 5;
	private static final int DEFAULT_CHAR_PRINT_WIDTH = 1;
	private int printWidth = 0;
	
	private int mainPc;
    private static final String MAIN_METHOD = "main";
    
    private final List<String> specialFunctionNames = Arrays.asList("len", "chr", "ord");

    private boolean methodHasReturn = false;
    
    private List<Obj> designatorStatementAssignArrayObjects = null;
    private List<Integer> designatorStatementAssignArrayObjectIndexes = null;
    private int designatorStatementAssignArrayObjectCnt = 0;
    
    // Current statement type (used for jump FIXUP)
    private enum SpecialStatement {IF_ELSE_STMT, WHILE_STMT, FOREACH_STMT};
    private Stack<SpecialStatement> specialStatementStack = new Stack<>();
    
    // Current loop type (used for CONTINUE & BREAK)
    private enum Loop {WHILE_LOOP, FOREACH_LOOP};
    private Stack<Loop> loopStack = new Stack<>();
    
    // IfElse stacks
    private Stack<Integer> skipElseBlockFixupJumpAddresses = new Stack<>();
    private Stack<Integer> skipIfBlockFixupJumpAddresses = new Stack<>();
    
    // While stacks
    private Stack<Integer> skipWhileBlockFixupJumpAddresses = new Stack<>();
    private Stack<Integer> begginingOfWhileBlockJumpAddresses = new Stack<>();
    private Stack<List<Integer>> breakWhileFixupJumpAddresses = new Stack<>();
    
    // Foreach stacks
    private Stack<Integer> skipForeachBlockFixupJumpAddresses = new Stack<>();
    private Stack<Integer> begginingOfForeachBlockJumpAddresses = new Stack<>();
    private Stack<List<Integer>> breakForeachFixupJumpAddresses = new Stack<>();


    
	// ~~~~~~~~~~~~~~~~~~~ Util ~~~~~~~~~~~~~~~~~~~
        
	public int getMainPc() {
		return mainPc;
	}
	
	public int getAddopOperatorCode(Class operatorClass) {
		if (operatorClass == AddopPlus.class) {
			return Code.add;
		} else if (operatorClass == AddopMinus.class) {
			return Code.sub;
		} else {
			// ERROR
			Code.put(Code.trap);
			return 10;
		}
	}
	
	public int getMulopOperatorCode(Class operatorClass) {
		if (operatorClass == AddopMul.class) {
			return Code.mul;
		} else if (operatorClass == AddopDiv.class) {
			return Code.div;
		} else if (operatorClass == AddopMod.class) {
			return Code.rem;
		} else {
			// ERROR
			Code.put(Code.trap);
			return 11;
		}
	}
	
	public int getRelopComparisonOperatorCode(Class operatorClass) {
		if (operatorClass == RelopGreater.class) {
			return Code.gt;
		} else if (operatorClass == RelopGreaterEquals.class) {
			return Code.ge;
		} else if (operatorClass == RelopLess.class) {
			return Code.lt;
		} else if (operatorClass == RelopLessEquals.class) {
			return Code.le;
		} else {
			// ERROR
			Code.put(Code.trap);
			return 12;
		}
	}
	
	public int getRelopEqualityOperatorCode(Class operatorClass) {
		if (operatorClass == RelopEquals.class) {
			return Code.eq;
		} else if (operatorClass == RelopNotEquals.class) {
			return Code.ne;
		} else {
			// ERROR
			Code.put(Code.trap);
			return 13;
		}
	}
	
	public void processSpecialFunction(String functionName) {
		if (functionName.equals("len")) {
			Code.put(Code.arraylength);
		} else {
			// No need to put anything on exprStack
		}
	}
	
	public int getJumpCondition(int condition) {
		return Code.jcc + condition;
	}
	
	// ~~~~~~~~~~~~~~~~~~~ MethodDecl & Return ~~~~~~~~~~~~~~~~~~~
    	
	public void visit(MethodName methodName) {
		if (methodName.getMethodName().equals(MAIN_METHOD)) {
			mainPc = Code.pc;
		}
		methodName.obj.setAdr(Code.pc);		
		int formParamCount = methodName.obj.getLevel();
		int totalVarCount = methodName.obj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(formParamCount);
		Code.put(totalVarCount);
	}
	
	public void visit(StatementReturnExpr statementReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		methodHasReturn = true;
	}
	
	public void visit(StatementReturnVoid statementReturnVoid) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		methodHasReturn = true;
	}
	
	public void visit(MethodDecl methodDecl) {
		if (!methodHasReturn) {
			if (methodDecl.getMethodName().obj.getType() != Tab.noType) {
				// ERROR
				Code.put(Code.trap);
				Code.put(0);	
			} else {
				Code.put(Code.exit);
				Code.put(Code.return_);
			}
		}
		methodHasReturn = false;
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement Read ~~~~~~~~~~~~~~~~~~~
	
	public void visit(StatementRead statementRead) {
		Obj designatorObj = statementRead.getDesignator().obj;
		if (designatorObj.getType() == Tab.charType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(designatorObj);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement Print ~~~~~~~~~~~~~~~~~~~
	
	public void visit(StatementPrint statementPrint) {
		int width;
		if (printWidth != 0) {
			width = printWidth;
		} else if (statementPrint.getExpr().struct == Tab.charType) {
			width = DEFAULT_CHAR_PRINT_WIDTH;
		} else {
			width = DEFAULT_INT_PRINT_WIDTH;
		}
		Code.loadConst(width);
		if (statementPrint.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
		printWidth = 0;
	}
	
	public void visit(PrintNumConstOptionalExists printNumConstOptionalExists) {
		printWidth = printNumConstOptionalExists.getNumConst();
	}
	
	// ~~~~~~~~~~~~~~~~~~~ DesignatorStatement ~~~~~~~~~~~~~~~~~~~

	public void visit(DesignatorAssignopExpression designatorAssignopExpression) {
		Code.store(designatorAssignopExpression.getDesignator().obj);
	}
	
	public void visit(DesignatorActionMethodCall designatorActionMethodCall) {
		Obj methodDesignator = designatorActionMethodCall.getDesignator().obj;
		if (specialFunctionNames.contains(methodDesignator.getName())) {
			processSpecialFunction(methodDesignator.getName());
		} else {
			int offset = methodDesignator.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
		// Return value is not cleared by caller
		if(methodDesignator.getType() != Tab.noType){
			Code.put(Code.pop);
		}
	}
	
	public void visit(DesignatorActionInc designatorActionInc) {
		Obj designatorObj = designatorActionInc.getDesignator().obj;
		if (designatorObj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designatorObj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorObj);
	}
	
	public void visit(DesignatorActionDec designatorActionDec) {
		Obj designatorObj = designatorActionDec.getDesignator().obj;
		if (designatorObj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designatorObj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorObj);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ DesignatorStatementAssignArray ~~~~~~~~~~~~~~~~~~~
	
	public void visit(DesignatorStatementAssignArray designatorStatementAssignArray) {
		Obj designatorArrRight = designatorStatementAssignArray.getDesignator().obj;
		
		// Check for Runtime Error, if number of args > arrLength
		Code.load(designatorStatementAssignArray.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.loadConst(designatorStatementAssignArrayObjectCnt);
		//int jumpToFix = Code.pc + 1;
		Code.put(getJumpCondition(Code.ge));	// if (arrLength >= cnt) skip trap;
		int jumpToFix = Code.pc;
		Code.put2(0);
		Code.put(Code.trap);
		Code.put(1);
		Code.fixup(jumpToFix);
		
		// TAKE CARE OF THIS! Inverse because array element assignment happens on stack
		for (int i = designatorStatementAssignArrayObjects.size() - 1; i >= 0; i--) {
			int arrayIndex = designatorStatementAssignArrayObjectIndexes.get(i);
			Obj designatorLeft = designatorStatementAssignArrayObjects.get(i);
			Code.load(designatorArrRight);
			Code.loadConst(arrayIndex);
			if (designatorArrRight.getType().getElemType() == Tab.charType) {
				Code.put(Code.baload);
			} else {
				Code.put(Code.aload);
			}
			Code.store(designatorLeft);
		}
	}
	
	public void visit(DesignatorStatementAssignArrayStart designatorStatementAssignArrayStart) {
		designatorStatementAssignArrayObjects = new ArrayList<>();
		designatorStatementAssignArrayObjectIndexes = new ArrayList<>();
		designatorStatementAssignArrayObjectCnt = 0;
	}
	
	public void visit(DesignatorOptionalExist designatorOptionalExist) {
		designatorStatementAssignArrayObjects.add(designatorOptionalExist.getDesignator().obj);
		designatorStatementAssignArrayObjectIndexes.add(designatorStatementAssignArrayObjectCnt);
		designatorStatementAssignArrayObjectCnt++;
	}
	
	public void visit(DesignatorOptionalEmpty designatorOptionalEmpty) {
		designatorStatementAssignArrayObjectCnt++;
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Designator ~~~~~~~~~~~~~~~~~~~
	
	public void visit(DesignatorArray designatorArray) {
		// Since Load and Store for Arrays require Adr, Index values on exprStack, put them here
		// Index is put from Expr, and Adr is put here
		// designatorArray is still Var gere, changed to Elem in DesignatorSquareBrackets visit
		Code.load(designatorArray.getDesignator().obj);
	}
	
	public void visit(DesignatorSingle designatorSingle) {
		// Already loading this in other visit methods
		// For arrays, loading aload/baload in other methods (that's why we need to load Adr/Value before)
	}
	
	public void visit(DesignatorDot DesignatorDot) {
		// Level C
	}
		
	// ~~~~~~~~~~~~~~~~~~~ Expr ~~~~~~~~~~~~~~~~~~~
	
	public void visit(ExprMultiple exprMultiple) {
		Code.put(getAddopOperatorCode(exprMultiple.getAddop().getClass()));
	}

	public void visit(ExprSingleMinus exprSingleMinus) {
		Code.put(Code.neg);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Term ~~~~~~~~~~~~~~~~~~~

	public void visit(TermMultiple termMultiple) {
		Code.put(getMulopOperatorCode(termMultiple.getMulop().getClass()));
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Factor ~~~~~~~~~~~~~~~~~~~
	
	public void visit(FactorDesignator factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
	}
	
	public void visit(FactorDesignatorMethodCall factorDesignatorMethodCall) {
		Obj methodDesignator = factorDesignatorMethodCall.getDesignator().obj;
		if (specialFunctionNames.contains(methodDesignator.getName())) {
			processSpecialFunction(methodDesignator.getName());
		} else {
			int offset = methodDesignator.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}

	public void visit(FactorNumConst factorNumConst) {
		Code.loadConst(factorNumConst.getN1());
	}
	
	public void visit(FactorCharConst factorCharConst) {
		Code.loadConst(factorCharConst.getC1());
	}
	
	public void visit(FactorBoolConst factorBoolConst) {
		Code.loadConst(factorBoolConst.getB1() ? 1 : 0);
	}
	
	public void visit(FactorNewArray factorNewArray) {
		// Array length already on ExprStack - visited Expr
		Code.put(Code.newarray);
		if (factorNewArray.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	// TODO check TRAP error numbers
	
	// ~~~~~~~~~~~~~~~~~~~ Statement If Else ~~~~~~~~~~~~~~~~~~~
	
	public void visit(IfStatementStart ifStatementStart) {
		specialStatementStack.push(SpecialStatement.IF_ELSE_STMT);
	}
	
	public void visit(PlaceAfterIfCondition placeAfterIfCondition) {
		// putFalseJump already placed in CondFact
	}
	
	public void visit(StatementIfElse statementIfElse) {
		// Pay attention to this!
		Code.fixup(skipElseBlockFixupJumpAddresses.pop());
		specialStatementStack.pop();
	}
	
	public void visit(StatementIf statementIf) {
		// Pay attention to this!
		Code.fixup(skipElseBlockFixupJumpAddresses.pop());
		specialStatementStack.pop();
	}
	
	public void visit(PlaceAfterIfBlock placeAfterIfBlock) {
		Code.putJump(0);
		skipElseBlockFixupJumpAddresses.push(Code.pc - 2);
		Code.fixup(skipIfBlockFixupJumpAddresses.pop());
	}
	
	public void visit(PlaceAfterElseBlock placeAfterElseBlock) {
		// Pay attention to this!
		//Code.fixup(skipElseBlockFixupJumpAddresses.pop());
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement While ~~~~~~~~~~~~~~~~~~~

	public void visit(StatementWhileStart statementWhileStart) {
		specialStatementStack.push(SpecialStatement.WHILE_STMT);
		loopStack.push(Loop.WHILE_LOOP);
		breakWhileFixupJumpAddresses.push(new ArrayList<>());
		begginingOfWhileBlockJumpAddresses.push(Code.pc);
	}
	
	public void visit(PlaceAfterWhileBlock placeAfterWhileBlock) {
		Code.putJump(begginingOfWhileBlockJumpAddresses.peek());
		begginingOfWhileBlockJumpAddresses.pop();
		Code.fixup(skipWhileBlockFixupJumpAddresses.peek());
		skipWhileBlockFixupJumpAddresses.pop();
	}
	
	public void visit(StatementWhile statementWhile) {
		List<Integer> breakJmpFixupList = breakWhileFixupJumpAddresses.pop();
		for (int addr : breakJmpFixupList) {
			Code.fixup(addr);
		}
		specialStatementStack.pop();
		loopStack.pop();
	}
		
	// ~~~~~~~~~~~~~~~~~~~ Statement Continue ~~~~~~~~~~~~~~~~~~~

	public void visit(StatementContinue statementContinue) {
		Loop currentLoop = loopStack.peek();
		if (currentLoop == Loop.WHILE_LOOP) {
			Code.putJump(begginingOfWhileBlockJumpAddresses.peek());
		} else if (currentLoop == Loop.FOREACH_LOOP) {
			Code.putJump(begginingOfForeachBlockJumpAddresses.peek());
		}
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement Break ~~~~~~~~~~~~~~~~~~~
	
	public void visit(StatementBreak statementBreak) {
		Code.putJump(0);
		Loop currentLoop = loopStack.peek();
		if (currentLoop == Loop.WHILE_LOOP) {
			breakWhileFixupJumpAddresses.peek().add(Code.pc - 2);
		} else if (currentLoop == Loop.FOREACH_LOOP) {
			breakForeachFixupJumpAddresses.peek().add(Code.pc - 2);
		}
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement Foreach ~~~~~~~~~~~~~~~~~~~
	
	public void visit(StatementForeachStart statementForeachStart) {
		specialStatementStack.push(SpecialStatement.FOREACH_STMT);
		loopStack.push(Loop.FOREACH_LOOP);
		breakForeachFixupJumpAddresses.push(new ArrayList<>());
	}
	
	public void visit(ForeachStatementBeginning foreachStatementBeginning) {
		Obj arrayDesignator = foreachStatementBeginning.getDesignator().obj;
		Obj varDesignator = foreachStatementBeginning.getForeachVarDesignator().obj;
		
															// STACK
		Code.load(arrayDesignator);							// arrAddr
		Code.loadConst(-1);									// arrAddr, i
		
		begginingOfForeachBlockJumpAddresses.push(Code.pc); // Beginning of the loop (before inc)
		
		Code.loadConst(1);									// arrAddr, i, 1	
		Code.put(Code.add);									// arrAddr, i = i + 1
		
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i, arrAddr, i

		Code.put(Code.pop);									// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.arraylength);							// arrAddr, i, arrAddr, i, arrLength
		
		Code.put(getJumpCondition(Code.ge));				// arrAddr, i, arrAddr
		skipForeachBlockFixupJumpAddresses.push(Code.pc);	// FIXUP THIS -> Change to End
		Code.put2(0);	// ADDRESS HAS 2 BYTES!				// arrAddr, i, arrAddr
		
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.pop);									// arrAddr, i, arrAddr, i
		Code.put(Code.aload);								// arrAddr, i, arr[i]
		Code.store(varDesignator);							// arrAddr, i
		
		// Next, statements are handled
	}
	
	public void visit(PlaceAfterForeachBlock placeAfterForeachBlock) {
		Code.putJump(begginingOfForeachBlockJumpAddresses.peek());
		begginingOfForeachBlockJumpAddresses.pop();
		Code.fixup(skipForeachBlockFixupJumpAddresses.peek());
		skipForeachBlockFixupJumpAddresses.pop();
	}
	
	public void visit(ForeachStatement foreachStatement) {
		List<Integer> breakJmpFixupList = breakForeachFixupJumpAddresses.pop();
		for (int addr : breakJmpFixupList) {
			Code.fixup(addr);
		}
		
		// Cleanup stack
		Code.put(Code.pop);			// i
		Code.put(Code.pop);			// arrAddr
		
		specialStatementStack.pop();
		loopStack.pop();
	}
	
	// ~~~~~~~~~~~~~~~~~~~ CondTerm ~~~~~~~~~~~~~~~~~~~

	
	
	// ~~~~~~~~~~~~~~~~~~~ CondFact ~~~~~~~~~~~~~~~~~~~
	
	// Must be bool, checked in semantic analysis
	public void visit(CondFactSingle condFactSingle) {
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		saveFixupJumpAddress();
	}
	
	public void visit(CondFactMultipleEquality condFactMultipleEquality) {
		int cond = getRelopEqualityOperatorCode(condFactMultipleEquality.getRelopEquality().getClass());
		Code.putFalseJump(cond, 0);
		saveFixupJumpAddress();
	}
	
	public void visit(CondFactMultipleComparaison condFactMultipleComparaison) {
		int cond = getRelopComparisonOperatorCode(condFactMultipleComparaison.getRelopComparison().getClass());
		Code.putFalseJump(cond, 0);
		saveFixupJumpAddress();
	}
	
	// Put pc as fixup jump address for current stmt
	private void saveFixupJumpAddress() {
		SpecialStatement currentStatement = specialStatementStack.peek();
		if (currentStatement == SpecialStatement.IF_ELSE_STMT) {
			skipIfBlockFixupJumpAddresses.push(Code.pc - 2);
		} else if (currentStatement == SpecialStatement.WHILE_STMT) {
			skipWhileBlockFixupJumpAddresses.push(Code.pc - 2);
		}
	}
	
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~ MODS ~~~~~~~~~~~~~~~~~~~~~~

	// ARRMAX
	public void visit(FactorArrMax statementArrMax) {
		Obj arrayDesignator = statementArrMax.getDesignator().obj;
		Obj iteratorDesignator = statementArrMax.getDesignator1().obj;
		Obj targetDesignator = statementArrMax.getDesignator2().obj;
		
		// i = 0;
		Code.loadConst(0);
		Code.store(iteratorDesignator);
		
		// Get the beginning PC (for jump back)
		int beginningPC = Code.pc;
		
		// if (i >= arrLength) jump to end;
		Code.load(iteratorDesignator);
		Code.load(arrayDesignator);
		Code.put(Code.arraylength);
		Code.put(getJumpCondition(Code.ge));
		int jumpToEnd = Code.pc;
		Code.put2(0);
		
		// if (arr[i] <= max) goto increment (skip max = arr[i])
		Code.load(arrayDesignator);
		Code.load(iteratorDesignator);
		Code.put(Code.aload);
		Code.load(targetDesignator);
		Code.put(getJumpCondition(Code.le));
		int jumpToInc = Code.pc;
		Code.put2(0);
		
		// max = arr[i]
		Code.load(arrayDesignator);
		Code.load(iteratorDesignator);
		Code.put(Code.aload);
		Code.store(targetDesignator);
		
		// i++ (increment)
		Code.fixup(jumpToInc);
		Code.load(iteratorDesignator);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(iteratorDesignator);
		
		// Back to beginning
		Code.putJump(beginningPC);
		
		// End
		Code.fixup(jumpToEnd);
		Code.load(targetDesignator);
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~ /MODS ~~~~~~~~~~~~~~~~~~~~~~
}
