package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

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
    
    // Current statement type (used for jump fixup)
    private enum SpecialStatement {IF_ELSE_STMT, WHILE_STMT, FOREACH_STMT};
    private Stack<SpecialStatement> specialStatementStack = new Stack<>();
    
    // Current loop type (used for continue & break jumps)
    private enum Loop {WHILE_LOOP, FOREACH_LOOP};
    private Stack<Loop> loopStack = new Stack<>();
    
    
    // IF ELSE
    
    // Holds addresses to fixup
    private Stack<Integer> skipElseBlockFixupJumpAddresses = new Stack<>();
    private Stack<List<Integer>> ifConditionIsFalseFixupJumpAddresses = new Stack<>();
    private Stack<List<Integer>> beginningOfIfBlockFixupJumpAddresses = new Stack<>();
    
    
    // WHILE
    
    // Holds addresses to fixup
    private Stack<List<Integer>> breakWhileFixupJumpAddresses = new Stack<>();
    private Stack<List<Integer>> whileConditionIsFalseFixupJumpAddresses = new Stack<>();
    private Stack<List<Integer>> beginningOfWhileBlockFixupJumpAddresses = new Stack<>();

    // Holds real addresses
    private Stack<Integer> beginningOfWhileBlockJumpAddresses = new Stack<>();

    
    // FOREACH
    
    // Holds addresses to fixup
    private Stack<Integer> skipForeachBlockFixupJumpAddresses = new Stack<>();
    private Stack<List<Integer>> breakForeachFixupJumpAddresses = new Stack<>();
    
    // Holds real addresses
    private Stack<Integer> beginningOfForeachBlockJumpAddresses = new Stack<>();
    

    
	// ~~~~~~~~~~~~~~~~~~~ Util ~~~~~~~~~~~~~~~~~~~
        
	public int getMainPc() {
		return mainPc;
	}
	
	public int getAddopOperatorCode(Class<? extends Addop> operatorClass) {
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
	
	public int getMulopOperatorCode(Class<? extends Mulop> operatorClass) {
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
	
	public int getRelopComparisonOperatorCode(Class<? extends RelopComparison> operatorClass) {
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
	
	public int getRelopEqualityOperatorCode(Class<? extends RelopEquality> operatorClass) {
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
		
		// Runtime error check
		Code.load(designatorStatementAssignArray.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.loadConst(designatorStatementAssignArrayObjectCnt);
		Code.put(getJumpCondition(Code.ge));	// if (arrLength >= cnt) skip trap;
		int jumpToFix = Code.pc;
		Code.put2(0);
		Code.put(Code.trap);
		Code.put(1);
		Code.fixup(jumpToFix);
		
		// Inverse because array element assignment happens on stack, in reverse order
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
		Code.load(designatorArray.getDesignator().obj);
	}
	
	public void visit(DesignatorSingle designatorSingle) {
		// Empty
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
		// Array length already on exprStack - visited Expr
		Code.put(Code.newarray);
		if (factorNewArray.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
		
	// ~~~~~~~~~~~~~~~~~~~ Statement If Else ~~~~~~~~~~~~~~~~~~~
	
	public void visit(IfStatementStart ifStatementStart) {
		specialStatementStack.push(SpecialStatement.IF_ELSE_STMT);
		
		// For Conditions
		beginningOfIfBlockFixupJumpAddresses.push(new ArrayList<>());
		ifConditionIsFalseFixupJumpAddresses.push(new ArrayList<>());
	}
	
	public void visit(PlaceAfterIfCondition placeAfterIfCondition) {
		List<Integer> addressesToFix = beginningOfIfBlockFixupJumpAddresses.peek();
		for (int addr : addressesToFix) {
			Code.fixup(addr);
		}
		beginningOfIfBlockFixupJumpAddresses.peek().clear();
	}
	
	public void visit(StatementIfElse statementIfElse) {
		
		// Need to patch skipElse jump
		Code.fixup(skipElseBlockFixupJumpAddresses.peek());
		skipElseBlockFixupJumpAddresses.pop();
		
		specialStatementStack.pop();
		
		// Pop for Conditions
		beginningOfIfBlockFixupJumpAddresses.pop();
		ifConditionIsFalseFixupJumpAddresses.pop();
	}
	
	public void visit(StatementIf statementIf) {
		
		// No need to patch skipElse jump
		specialStatementStack.pop();
		
		// Pop for Conditions
		beginningOfIfBlockFixupJumpAddresses.pop();
		ifConditionIsFalseFixupJumpAddresses.pop();
	}
	
	public void visit(PlaceAfterIfBlock placeAfterIfBlock) {
		
		// Set jump after else branch
		if (placeAfterIfBlock.getParent().getParent().getClass() == StatementIfElse.class) {
			Code.putJump(0);
			skipElseBlockFixupJumpAddresses.push(Code.pc - 2);
		}
		
		// Fix jumps to else (if condition is false)
		List<Integer> addressesToFix = ifConditionIsFalseFixupJumpAddresses.peek();
		for (int addr : addressesToFix) {
			Code.fixup(addr);
		}
		ifConditionIsFalseFixupJumpAddresses.peek().clear();
	}
	
	public void visit(PlaceAfterElseBlock placeAfterElseBlock) {
		// Empty
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement While ~~~~~~~~~~~~~~~~~~~

	public void visit(StatementWhileStart statementWhileStart) {
		specialStatementStack.push(SpecialStatement.WHILE_STMT);
		loopStack.push(Loop.WHILE_LOOP);
		breakWhileFixupJumpAddresses.push(new ArrayList<>());
		beginningOfWhileBlockJumpAddresses.push(Code.pc);
		
		// For Conditions
		beginningOfWhileBlockFixupJumpAddresses.push(new ArrayList<>());
		whileConditionIsFalseFixupJumpAddresses.push(new ArrayList<>());
	}
	
	public void visit(StatementWhile statementWhile) {
		
		// Set default jump to beginning
		Code.putJump(beginningOfWhileBlockJumpAddresses.peek());
		beginningOfWhileBlockJumpAddresses.pop();
		
		// Fixup jumps to end
		List<Integer> falseConditionJmpFixupList = whileConditionIsFalseFixupJumpAddresses.peek();
		for (int addr : falseConditionJmpFixupList) {
			Code.fixup(addr);
		}
		whileConditionIsFalseFixupJumpAddresses.peek().clear();
		
		// Fixup break jumps
		List<Integer> breakJmpFixupList = breakWhileFixupJumpAddresses.pop();
		for (int addr : breakJmpFixupList) {
			Code.fixup(addr);
		}
		
		// Pop state identifiers
		specialStatementStack.pop();
		loopStack.pop();
		
		// Pop for Conditions
		beginningOfWhileBlockFixupJumpAddresses.pop();
		whileConditionIsFalseFixupJumpAddresses.pop();
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statement Foreach ~~~~~~~~~~~~~~~~~~~
	
	public void visit(ForeachStatementBeginning foreachStatementBeginning) {
		// Init state
		specialStatementStack.push(SpecialStatement.FOREACH_STMT);
		loopStack.push(Loop.FOREACH_LOOP);
		breakForeachFixupJumpAddresses.push(new ArrayList<>());
		
		Obj arrayDesignator = foreachStatementBeginning.getDesignator().obj;
		Obj varDesignator = foreachStatementBeginning.getForeachVarDesignator().obj;
		
															// STACK
		Code.load(arrayDesignator);							// arrAddr
		Code.loadConst(-1);									// arrAddr, i
		
		beginningOfForeachBlockJumpAddresses.push(Code.pc); // Beginning of the loop (before inc)
		
		Code.loadConst(1);									// arrAddr, i, 1	
		Code.put(Code.add);									// arrAddr, i = i + 1
		
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i, arrAddr, i

		Code.put(Code.pop);									// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.arraylength);							// arrAddr, i, arrAddr, i, arrLength
		
		// WHEN JUMPING, NEED TO CLEANUP 3 ADDRESSES
		Code.put(getJumpCondition(Code.ge));				// arrAddr, i, arrAddr
		skipForeachBlockFixupJumpAddresses.push(Code.pc);	// FIXUP THIS -> Change to End
		Code.put2(0);	// ADDRESS HAS 2 BYTES!				// arrAddr, i, arrAddr
		
		Code.put(Code.dup2);								// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.pop);									// arrAddr, i, arrAddr, i
		Code.put(Code.aload);								// arrAddr, i, arr[i]
		Code.store(varDesignator);							// arrAddr, i
		
		// Next, statements are handled
	}
		
	public void visit(ForeachStatement foreachStatement) {
		// Set default jump to beginning
		Code.putJump(beginningOfForeachBlockJumpAddresses.peek());
		beginningOfForeachBlockJumpAddresses.pop();
		// Fixup jumps to end
		Code.fixup(skipForeachBlockFixupJumpAddresses.peek());
		skipForeachBlockFixupJumpAddresses.pop();
		// Fixup break jumps
		List<Integer> breakJmpFixupList = breakForeachFixupJumpAddresses.pop();
		for (int addr : breakJmpFixupList) {
			Code.fixup(addr);
		}
		
		// Cleanup stack
		Code.put(Code.pop);			// arrAddr
		Code.put(Code.pop);			// i
		Code.put(Code.pop);			// arrAddr
		
		specialStatementStack.pop();
		loopStack.pop();
	}
		
	// ~~~~~~~~~~~~~~~~~~~ Statement Continue ~~~~~~~~~~~~~~~~~~~

	public void visit(StatementContinue statementContinue) {
		Loop currentLoop = loopStack.peek();
		if (currentLoop == Loop.WHILE_LOOP) {
			Code.putJump(beginningOfWhileBlockJumpAddresses.peek());
		} else if (currentLoop == Loop.FOREACH_LOOP) {
			Code.putJump(beginningOfForeachBlockJumpAddresses.peek());
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
	
	// ~~~~~~~~~~~~~~~~~~~ Condition ~~~~~~~~~~~~~~~~~~~
	
	// We already have falseJump from CondFact that jumps to: next condition in OR / then branch / after while loop
	public void visit(PlaceAfterFirstConditionInOr placeAfterFirstConditionInOr) {
		
		// If condition in OR is true, jump to: if branch / while branch
		Code.putJump(0);
		SpecialStatement currentStatement = specialStatementStack.peek();
		if (currentStatement == SpecialStatement.IF_ELSE_STMT) {
			beginningOfIfBlockFixupJumpAddresses.peek().add(Code.pc - 2);
		} else if (currentStatement == SpecialStatement.WHILE_STMT) {
			beginningOfWhileBlockFixupJumpAddresses.peek().add(Code.pc - 2);
		}
		
		// Fixup all false jumps from conditions in AND (this is fixed up to either: next condition in OR / then branch / after while loop
		if (currentStatement == SpecialStatement.IF_ELSE_STMT) {
			List<Integer> addressesToFix = ifConditionIsFalseFixupJumpAddresses.peek();
			for (int addr : addressesToFix) {
				Code.fixup(addr);
			}
			ifConditionIsFalseFixupJumpAddresses.peek().clear();
		} else if (currentStatement == SpecialStatement.WHILE_STMT) {
			List<Integer> addressesToFix = whileConditionIsFalseFixupJumpAddresses.peek();
			for (int addr : addressesToFix) {
				Code.fixup(addr);
			}
			whileConditionIsFalseFixupJumpAddresses.peek().clear();
		}
	}
		
	// ~~~~~~~~~~~~~~~~~~~ CondTerm ~~~~~~~~~~~~~~~~~~~
	
	public void visit(PlaceAfterFirstConditionInAnd placeAfterFirstConditionInAnd) {
		// falseJump already set in CondFact
	}
	
	// ~~~~~~~~~~~~~~~~~~~ CondFact ~~~~~~~~~~~~~~~~~~~
	
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
	
	private void saveFixupJumpAddress() {
		SpecialStatement currentStatement = specialStatementStack.peek();
		if (currentStatement == SpecialStatement.IF_ELSE_STMT) {
			ifConditionIsFalseFixupJumpAddresses.peek().add(Code.pc - 2);
		} else if (currentStatement == SpecialStatement.WHILE_STMT) {
			whileConditionIsFalseFixupJumpAddresses.peek().add(Code.pc - 2);
		}
	}
		
	
	
	
	
	
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~ ADDITIONAL ~~~~~~~~~~~~~~~~~~~~~~

	// ARRMAX (array must have len > 0)
	public void visit(StatementArrMax statementArrMax) {
		Obj arrayDesignator = statementArrMax.getArrMaxArrDesignator().obj;
		Obj maxVarDesignator = statementArrMax.getArrMaxVarDesignator().obj;
		
		// Set max = arr[0]
		Code.load(arrayDesignator);
		Code.loadConst(0);
		Code.put(Code.aload);
		Code.store(maxVarDesignator);
		
		
		Code.load(arrayDesignator);						// arrAddr
		Code.loadConst(-1);								// arrAddr, i
		
		int loopStart = Code.pc;
		
		Code.loadConst(1);								// arrAddr, i, 1
		Code.put(Code.add);								// arrAddr, i
		
		Code.put(Code.dup2);							// arrAddr, i, arrAddr, i
		Code.put(Code.dup2);							// arrAddr, i, arrAddr, i, arrAddr, i

		Code.put(Code.pop);								// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.arraylength);						// arrAddr, i, arrAddr, i, arrLength
		
		Code.put(getJumpCondition(Code.ge));			
		int fixupJumpToFinalEnd = Code.pc;
		Code.put2(0);									// arrAddr, i, arrAddr
		
		Code.put(Code.dup2);							// arrAddr, i, arrAddr, i, arrAddr
		Code.put(Code.pop);								// arrAddr, i, arrAddr, i
		Code.put(Code.aload);							// arrAddr, i, arr[i]
		
		Code.put(Code.dup);								// arrAddr, i, arr[i], arr[i]
		
		Code.load(maxVarDesignator);					// arrAddr, i, arr[i], arr[i], max
		
		Code.put(getJumpCondition(Code.le));
		int fixupJumpToLoopEndBeforePop = Code.pc;
		Code.put2(0); 									// arrAddr, i, arr[i]
		
		Code.store(maxVarDesignator);					// arrAddr, i		
		Code.putJump(0);
		int fixupJumpToLoopEndAfterPop = Code.pc - 2;
		
		Code.fixup(fixupJumpToLoopEndBeforePop);		
		Code.put(Code.pop);								// arrAddr, i
		Code.fixup(fixupJumpToLoopEndAfterPop);
		
		Code.putJump(loopStart);						// arrAddr, i
		
		Code.fixup(fixupJumpToFinalEnd);
		
		Code.put(Code.pop);								// arrAddr, i, arrAddr				
		Code.put(Code.pop);								// arrAddr, i
		Code.put(Code.pop);								// arrAddr
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~ /ADDITIONAL ~~~~~~~~~~~~~~~~~~~~~~
}
