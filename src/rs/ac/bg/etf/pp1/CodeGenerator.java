package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    
    
    private static final int FPPOS_FUNC_FORM_PARAM = 0;
    private static final int FPPOS_VAR = 1;
	private static final int FPPOS_LABEL = 2; 		// ADDITIONAL

    
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
	
    public int getNumberOfFormPars(Obj methodDesignator) {
    	int cnt = 0;
    	for (Obj symbol : methodDesignator.getLocalSymbols()) {
    		if (symbol.getFpPos() == FPPOS_FUNC_FORM_PARAM) {
    			cnt++;
    		}
    	}
    	return cnt;
    }
    
    public int getNumberOfVars(Obj methodDesignator) {
    	int cnt = 0;
    	for (Obj symbol : methodDesignator.getLocalSymbols()) {
    		if (symbol.getFpPos() == FPPOS_VAR) {
    			cnt++;
    		}
    	}
    	return cnt;
    }
    
    public int getNumberOfLabels(Obj methodDesignator) {
    	int cnt = 0;
    	for (Obj symbol : methodDesignator.getLocalSymbols()) {
    		if (symbol.getFpPos() == FPPOS_LABEL) {
    			cnt++;
    		}
    	}
    	return cnt;
    }
	
	// ~~~~~~~~~~~~~~~~~~~ MethodDecl & Return ~~~~~~~~~~~~~~~~~~~
    	
	public void visit(MethodName methodName) {
		if (methodName.getMethodName().equals(MAIN_METHOD)) {
			mainPc = Code.pc;
		}
		methodName.obj.setAdr(Code.pc);		
		//int formParamCount = methodName.obj.getLevel();
		//int totalVarCount = methodName.obj.getLocalSymbols().size();
		int formParamCount = getNumberOfFormPars(methodName.obj);
		int totalVarCount = formParamCount + getNumberOfVars(methodName.obj);
		System.out.println("Func local symbols size: " + totalVarCount);
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
		if (methodDecl.getMethodName().obj.getType() != Tab.noType && !methodHasReturn) {
			// ERROR
			Code.put(Code.trap);
			Code.put(0);	
		} 
		if (methodDecl.getMethodName().obj.getType() == Tab.noType) {
			// VOID
			Code.put(Code.exit);
			Code.put(Code.return_);
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
	
	// Arr

	public void visit(StatementArrMax statementArrMax) {	// DONE
		Obj arrayDesignator = statementArrMax.getArrMaxArrDesignator().obj;
		
		// Init max = arr[0]
		Code.load(arrayDesignator);				// arrAddr
		Code.loadConst(0);						// arrAddr, 0
		Code.put(Code.aload);					// max				max = arr[0]
		
		// Init i = -1
		Code.loadConst(-1);						// max, i

		// Loop beginning
		int loopBeginning = Code.pc;
		Code.loadConst(1);						// max, i, 1
		Code.put(Code.add);						// max, i  			i = i + 1
		
		// Check i >= arrLen
		Code.put(Code.dup);						// max, i, i
		Code.load(arrayDesignator);				// max, i, i, arrAddr
		Code.put(Code.arraylength);				// max, i, i, arrLen
		Code.put(getJumpCondition(Code.ge));	// max, i
		int fixupEndAddr = Code.pc;				// jump to end if i >= arrLen
		Code.put2(0);
		
		// Load arr[i]
		Code.load(arrayDesignator);				// max, i, arrAddr
		Code.put(Code.dup2);					// max, i, arrAddr, i, arrAddr
		Code.put(Code.pop);						// max, i, arrAddr, i
		Code.put(Code.aload);					// max, i, arr[i]
		
		// Arrange stack
		Code.put(Code.dup_x2);					// arr[i], max, i, arr[i]
		Code.put(Code.pop);						// arr[i], max, i
		Code.put(Code.dup_x2);					// i, arr[i], max, i
		Code.put(Code.pop);						// i, arr[i], max
		
		// Check arr[i] <= max
		Code.put(Code.dup2);					// i, arr[i], max, arr[i], max
		Code.put(getJumpCondition(Code.le));	// i, arr[i], max
		int fixupKeepMaxAddr = Code.pc;			// jump to keepMax if arr[i] <= max
		Code.put2(0);
		
		// Update max
		Code.put(Code.pop);						// i, arr[i]
		Code.put(Code.dup_x1);					// arr[i], i, arr[i]
		Code.put(Code.pop);						// arr[i], i
		Code.putJump(loopBeginning);			// jump to beginning
		
		// Keep max
		Code.fixup(fixupKeepMaxAddr);			
		Code.put(Code.dup_x2);					// max, i, arr[i], max
		Code.put(Code.pop);						// max, i, arr[i]
		Code.put(Code.pop);						// max, i
		Code.putJump(loopBeginning);			// jump to beginning
		
		Code.fixup(fixupEndAddr);				// End
		
		Code.put(Code.pop);						// max, i
		Code.loadConst(5);						// max, 5
		Code.put(Code.print);					// 
	}
	
	public void visit(ArrSortArrDesignator arrSortArrDesignator) {
		Obj arrDesignator = arrSortArrDesignator.obj;
		
		// init i
		Code.loadConst(-1);						// i
		
		// i loop begin
		int iLoopBegin = Code.pc;
		Code.loadConst(1);						// i, 1
		Code.put(Code.add);						// i
		
		// i condition check
		Code.put(Code.dup);						// i, i
		Code.load(arrDesignator);				// i, i, arrAddr
		Code.put(Code.arraylength);				// i, i, arrLen
		Code.put(getJumpCondition(Code.ge));	// i
		int jumpToEndFixup = Code.pc;
		Code.put2(0);							// i				// Jump to end
		
		// init j
		Code.put(Code.dup);						// i, j
		
		// j loop begin
		int jLoopBegin = Code.pc;
		Code.loadConst(1);						// i, j, 1
		Code.put(Code.add);						// i, j
		
		// j condition check
		Code.put(Code.dup);						// i, j, j
		Code.load(arrDesignator);				// i, j, j, arrAddr
		Code.put(Code.arraylength);				// i, j, j, arrLen
		Code.put(getJumpCondition(Code.lt));	// i, j
		int jumpToJLoopBodyFixup = Code.pc;
		Code.put2(0);							// i, j
		
		// pop & jump to i loop begin if j >= arrLen
		Code.put(Code.pop);						// i
		Code.putJump(iLoopBegin);				// i				// Jump to i loop begin
		
		// jLoopBody
		Code.fixup(jumpToJLoopBodyFixup);
		
		// Load arr[j]
		Code.put(Code.dup2);					// i, j, i, j
		Code.load(arrDesignator);				// i, j, i, j, arrAddr
		Code.put(Code.dup2);					// i, j, i, j, arrAddr, j, arrAddr
		Code.put(Code.pop);						// i, j, i, j, arrAddr, j
		Code.put(Code.aload);					// i, j, i, j, arr[j]
		
		// Load arr[i]
		Code.put(Code.dup_x2);					// i, j, arr[j], i, j, arr[j]
		Code.put(Code.pop);						// i, j, arr[j], i, j
		Code.put(Code.pop);						// i, j, arr[j], i
		Code.load(arrDesignator);				// i, j, arr[j], i, arrAddr
		Code.put(Code.dup2);					// i, j, arr[j], i, arrAddr, i, arrAddr
		Code.put(Code.pop);						// i, j, arr[j], i, arrAddr, i
		Code.put(Code.aload);					// i, j, arr[j], i, arr[i]
		Code.put(Code.dup_x1);					// i, j, arr[j], arr[i], i, arr[i]
		Code.put(Code.pop);						// i, j, arr[j], arr[i], i
		Code.put(Code.pop);						// i, j, arr[j], arr[i]
		
		// Compare arr[i], arr[j]
		Code.put(Code.dup2);					// i, j, arr[j], arr[i], arr[j], arr[i] 
		Code.put(getJumpCondition(Code.lt));	// i, j, arr[j], arr[i]
		int jumpToSwapFixup = Code.pc;			
		Code.put2(0);							// i, j, arr[j], arr[i]
		
		// pop & jump to j loop if arr[j] >= arr[i]
		Code.put(Code.pop);						// i, j, arr[j]
		Code.put(Code.pop);						// i, j
		Code.putJump(jLoopBegin);				// i, j
		
		
		// Swap									// i, j, arr[j], arr[i]
		Code.fixup(jumpToSwapFixup);
		
		// Store arr[j] = arr[i] + arr[j]
		Code.put(Code.add);						// i, j, arr[j] + arr[i]
		Code.put(Code.dup2);					// i, j, arr[j] + arr[i], j, arr[j] + arr[i]
		Code.load(arrDesignator);				// i, j, arr[j] + arr[i], j, arr[j] + arr[i], arrAddr
		Code.put(Code.dup_x2);					// i, j, arr[j] + arr[i], arrAddr, j, arr[j] + arr[i], arrAddr
		Code.put(Code.pop);						// i, j, arr[j] + arr[i], arrAddr, j, arr[j] + arr[i]
		Code.put(Code.astore);					// i, j, arr[j] + arr[i]
		
		// Load arr[i]
		Code.put(Code.pop);						// i, j
		Code.put(Code.dup2);					// i, j, i, j
		Code.put(Code.pop);						// i, j, i
		Code.load(arrDesignator);				// i, j, i, arrAddr
		Code.put(Code.dup2);					// i, j, i, arrAddr, i, arrAddr
		Code.put(Code.pop);						// i, j, i, arrAddr, i
		Code.put(Code.aload);					// i, j, i, arr[i]
		
		// Load arr[j]
		Code.put(Code.dup_x2);					// i, arr[i], j, i, arr[i]
		Code.put(Code.pop);						// i, arr[i], j, i
		Code.put(Code.pop);						// i, arr[i], j
		Code.load(arrDesignator);				// i, arr[i], j, arrAddr
		Code.put(Code.dup2);					// i, arr[i], j, arrAddr, j, arrAddr
		Code.put(Code.pop);						// i, arr[i], j, arrAddr, j
		Code.put(Code.aload);					// i, arr[i], j, arr[i] + arr[j]
		
		// Set arr[i] = arr[j](prev)
		Code.put(Code.dup_x2);					// i, arr[i] + arr[j], arr[i], j, arr[i] + arr[j]
		Code.put(Code.pop);						// i, arr[i] + arr[j], arr[i], j
		Code.put(Code.dup_x2);					// i, j, arr[i] + arr[j], arr[i], j
		Code.put(Code.pop);						// i, j, arr[i] + arr[j], arr[i]
		Code.put(Code.sub);						// i, j, arr[j]
		Code.put(Code.dup_x2);					// arr[j], i, j, arr[j]
		Code.put(Code.pop);						// arr[j], i, j
		Code.put(Code.dup_x2);					// j, arr[j], i, j
		Code.put(Code.pop);						// j, arr[j], i
		Code.put(Code.dup_x2);					// i, j, arr[j], i
		Code.put(Code.dup2);					// i, j, arr[j], i, arr[j], i
		Code.put(Code.pop);						// i, j, arr[j], i, arr[j]
		Code.load(arrDesignator);				// i, j, arr[j], i, arr[j], arrAddr
		Code.put(Code.dup_x2);					// i, j, arr[j], arrAddr, i, arr[j], arrAddr
		Code.put(Code.pop);						// i, j, arr[j], arrAddr, i, arr[j]
		Code.put(Code.astore);					// i, j, arr[j]							// arr[i] = arr[j]
		
		// Set arr[j] = arr[i](prev)			// i, j, arr[j]
		Code.put(Code.dup2);					// i, j, arr[j], j, arr[j]
		Code.put(Code.pop);						// i, j, arr[j], j
		Code.put(Code.dup_x2);					// i, j, j, arr[j], j
		Code.load(arrDesignator);				// i, j, j, arr[j], j, arrAddr
		Code.put(Code.dup2);					// i, j, j, arr[j], j, arrAddr, j, arrAddr
		Code.put(Code.pop);						// i, j, j, arr[j], j, arrAddr, j
		Code.put(Code.aload);					// i, j, j, arr[j], j, arr[i] + arr[j]
		Code.put(Code.dup_x2);					// i, j, j, arr[i] + arr[j], arr[j], j, arr[i] + arr[j]
		Code.put(Code.pop);						// i, j, j, arr[i] + arr[j], arr[j], j
		Code.put(Code.pop);						// i, j, j, arr[i] + arr[j], arr[j]
		Code.put(Code.sub);						// i, j, j, arr[i]
		Code.load(arrDesignator);				// i, j, j, arr[i], arrAddr
		Code.put(Code.dup_x2);					// i, j, arrAddr, j, arr[i], arrAddr
		Code.put(Code.pop);						// i, j, arrAddr, j, arr[i]
		Code.put(Code.astore);					// i, j									// arr[j] = arr[i]
		
		// Jump to jLoopBegin
		Code.putJump(jLoopBegin);				// i, j
		
		// End
		Code.fixup(jumpToEndFixup);				// i
		Code.put(Code.pop);						//
	}
	
	// Lbl
	
	private Map<String, List<Integer>> labelAddressesToFixup = new HashMap<>();
	private Map<String, Integer> labelRealAddresses = new HashMap<>();
	
	public void visit(StatementLabelDeclare statementLabelDeclare) {
		String labelName = statementLabelDeclare.getLabel().getLabelName();
		if (labelAddressesToFixup.containsKey(labelName)) {
			for (int addrToFixup : labelAddressesToFixup.get(labelName)) {
				Code.fixup(addrToFixup);
			}
		}
		labelRealAddresses.put(labelName, Code.pc);
	}
	
	public void visit(StatementGotoLabel statementGotoLabel) {
		String labelName = statementGotoLabel.getLabel().getLabelName();
		if (labelRealAddresses.containsKey(labelName)) {
			Code.putJump(labelRealAddresses.get(labelName));
		} else {
			if (!labelAddressesToFixup.containsKey(labelName)) {
				labelAddressesToFixup.put(labelName, new ArrayList<>());
			}
			Code.putJump(0);
			labelAddressesToFixup.get(labelName).add(Code.pc - 2);
		}
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~ /ADDITIONAL ~~~~~~~~~~~~~~~~~~~~~~
}
