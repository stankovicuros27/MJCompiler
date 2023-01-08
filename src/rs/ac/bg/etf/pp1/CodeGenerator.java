package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

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

    private boolean methodHasReturn = false;
    
    private List<Obj> designatorStatementAssignArrayObjects = null;
    private List<Integer> designatorStatementAssignArrayObjectIndexes = null;
    private int designatorStatementAssignArrayObjectCnt = 0;
    
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
			return Code.trap;
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
			return Code.trap;
		}
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
				Code.put(Code.trap);
				Code.put(1);	
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
		int offset = methodDesignator.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
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
	
	// TODO runtime error
	public void visit(DesignatorStatementAssignArray designatorStatementAssignArray) {
		Obj designatorArrRight = designatorStatementAssignArray.getDesignator().obj;
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
		designatorStatementAssignArrayObjectIndexes.add(designatorStatementAssignArrayObjectCnt++);
	}
	
	public void visit(DesignatorOptionalEmpty designatorOptionalEmpty) {
		designatorStatementAssignArrayObjectCnt++;
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Designator ~~~~~~~~~~~~~~~~~~~
	
	public void visit(DesignatorArray designatorArray) {
		// Need to load here, since Array is not processed elsewhere?
		Code.load(designatorArray.getDesignator().obj);
	}
	
	public void visit(DesignatorSingle designatorSingle) {
		// Already loading this in other visit methods
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
		int offset = methodDesignator.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
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
}
