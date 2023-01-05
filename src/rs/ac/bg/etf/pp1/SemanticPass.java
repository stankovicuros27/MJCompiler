package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> line ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> line ").append(line);
		log.info(msg.toString());
	}
	
    public boolean passed(){
    	return !errorDetected;
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ MY CODE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    private static final int FUNC_FORM_PARAM = 0;
    private static final int VAR = 1;
    private static final String MAIN_METHOD = "main";
    
    private boolean mainMethodExists = false;
    private int numberOfVars = 0;
    
    private Struct currentType = null;
    private Struct currentMethodReturnType = null;
    private Obj currentMethodObj = null;
	private List<Struct> currentActParsTypes = null;
	
	private int nestedLoopCnt = 0;

    
    // ~~~~~~~~~~~~~~~~~~~ Util ~~~~~~~~~~~~~~~~~~~
    
    public boolean checkIfSymbolExistsInCurrentScope(String symbolName, SyntaxNode info) {
    	if (Tab.currentScope().findSymbol(symbolName) != null) {
    		report_error("Symbol " + symbolName + " already exists in current scope!", info);
    		return true;
    	} 
    	return false;
    }
    
    public boolean checkIfTypeIsAssignableToCurrentType(Struct typeToAssign, SyntaxNode info) {
    	if (!typeToAssign.assignableTo(currentType)) {
    		report_error("Type is not assignable to current type!", info);
    		return false;
    	}
    	return true;
    }
    
    public boolean checkIfTypeIsAssignableToGivenType(Struct typeToAssign, Struct givenType, SyntaxNode info) {
    	if (!typeToAssign.assignableTo(givenType)) {
    		report_error("Type is not assignable to given type!", info);
    		return false;
    	}
    	return true;
    }
    
    public boolean checkIfActParsAreMatchingWithCurrentActPars(Obj methodDesignator) {
    	if (currentActParsTypes == null) {
    		report_error("Field currentActParsTypes is not initialized!", null);
    		return false;
    	}
    	List<Struct> methodFormParsTypes = getFormParsTypes(methodDesignator);
    	if (methodFormParsTypes.size() != currentActParsTypes.size()) {
    		report_error("Field currentActParsTypes doesn't match with Method's formParsTypes!", null);
    		return false;
    	}
    	for (int i = 0; i < methodFormParsTypes.size(); i++) {
    		Struct typeToAssign = currentActParsTypes.get(i);
    		Struct targetType = methodFormParsTypes.get(i);
    		if (!typeToAssign.assignableTo(targetType)) {
        		report_error("Field currentActParsTypes doesn't match with Method's formParsTypes!", null);
    			return false;
    		}
    	}
    	return true;
    }
    
    public List<Struct> getFormParsTypes(Obj methodDesignator) {
    	List<Struct> ret = new ArrayList<>();
    	for (Obj symbol : methodDesignator.getLocalSymbols()) {
    		if (symbol.getFpPos() == FUNC_FORM_PARAM) {
    			ret.add(symbol.getType());
    		}
    	}
    	return ret;
    }
    
    public int getNumberOfFormPars(Obj methodDesignator) {
    	int cnt = 0;
    	for (Obj symbol : methodDesignator.getLocalSymbols()) {
    		if (symbol.getFpPos() == FUNC_FORM_PARAM) {
    			cnt++;
    		}
    	}
    	return cnt;
    }
    
    public boolean checkIfMainExists() {
    	return mainMethodExists;
    }
    
    public boolean checkIfMainMethodIsValid(Obj mainMethod) {
    	if (mainMethod.getType() != Tab.noType) {
    		report_error("Main method can't have return type!", null);
			return false;
    	}
    	if (getNumberOfFormPars(mainMethod) > 0) {
    		report_error("Main method can't have FormPars!", null);
			return false;
    	}
    	return true;
    }
        
	// ~~~~~~~~~~~~~~~~~~~ Program ~~~~~~~~~~~~~~~~~~~
    
	public void visit(ProgramName programName) {
		mainMethodExists = false;
		programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
		Tab.openScope();
		report_info("ProgramName", programName);
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
		numberOfVars = Tab.currentScope.getnVars();
		if (!checkIfMainExists()) {
			report_error("Main method doesn't exist!", program);
			return;
		}
		report_info("Program", program);
	}
		
	// ~~~~~~~~~~~~~~~~~~~ ConstDecl and Constants ~~~~~~~~~~~~~~~~~~~
	
	public void visit(ConstDeclInnerListElement constDeclInnerListElement) {
		if (checkIfSymbolExistsInCurrentScope(constDeclInnerListElement.getConstName(), constDeclInnerListElement)) {
			return;
		}
		if (!checkIfTypeIsAssignableToCurrentType(constDeclInnerListElement.getConst().obj.getType(), constDeclInnerListElement)) {
			return;
		}
		Obj obj = Tab.insert(Obj.Con, constDeclInnerListElement.getConstName(), currentType);
		obj.setAdr(constDeclInnerListElement.getConst().obj.getAdr());
		constDeclInnerListElement.getConst().obj = obj;
		report_info("ConstDeclInnerListElement", constDeclInnerListElement);
	}
	
	public void visit(ConstNum constNum) {
		Obj constNumObj = new Obj(Obj.Con, "dummyInt", Tab.intType);
		constNumObj.setAdr(constNum.getValue());
		constNum.obj = constNumObj;
		report_info("ConstNum", constNum);
	}
	
	public void visit(ConstChar constChar) {
		Obj constCharObj = new Obj(Obj.Con, "dummyChar", Tab.charType);
		constCharObj.setAdr(constChar.getValue());
		constChar.obj = constCharObj;
		report_info("ConstChar", constChar);
	}
	
	public void visit(ConstBool constBool) {
		Obj constBoolObj = new Obj(Obj.Con, "dummyBool", TabWrapper.boolType);
		constBoolObj.setAdr(constBool.getValue() ? 1 : 0);
		constBool.obj = constBoolObj;
		report_info("ConstBool", constBool);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ VarDecl and Vars ~~~~~~~~~~~~~~~~~~~
	
	public void visit(VarDeclInnerListElementVar varDeclInnerListElementVar) {
		if (checkIfSymbolExistsInCurrentScope(varDeclInnerListElementVar.getVarName(), varDeclInnerListElementVar)) {
			return;
		}
		Tab.insert(Obj.Var, varDeclInnerListElementVar.getVarName(), currentType).setFpPos(VAR);
		report_info("VarDeclInnerListElementVar", varDeclInnerListElementVar);
	}
	
	public void visit(VarDeclInnerListElementArray varDeclInnerListElementArray) {
		if (checkIfSymbolExistsInCurrentScope(varDeclInnerListElementArray.getVarName(), varDeclInnerListElementArray)) {
			return;
		}
		Tab.insert(Obj.Var, varDeclInnerListElementArray.getVarName(), new Struct(Struct.Array, currentType)).setFpPos(VAR);
		report_info("VarDeclInnerListElementArray", varDeclInnerListElementArray);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ ClassDecl and Class ~~~~~~~~~~~~~~~~~~~	// LEVEL C
	
	// ~~~~~~~~~~~~~~~~~~~ MethodDecl and Methods ~~~~~~~~~~~~~~~~~~~
	
	public void visit(MethodReturnTypeHasType methodReturnTypeHasType) {
		currentMethodReturnType = currentType;
		report_info("MethodReturnTypeHasType", methodReturnTypeHasType);
	}
	
	public void visit(MethodReturnTypeIsVoid methodReturnTypeIsVoid) {
		currentMethodReturnType = Tab.noType;
		report_info("MethodReturnTypeIsVoid", methodReturnTypeIsVoid);
	}
	
	public void visit(MethodName methodName) {
		currentMethodObj = Tab.insert(Obj.Meth, methodName.getMethodName(), currentMethodReturnType);
		Tab.openScope();
		report_info("MethodName", methodName);
	}
	
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethodObj);
		Tab.closeScope();
		if (methodDecl.getMethodName().getMethodName().equals(MAIN_METHOD)) {
			mainMethodExists = true;
			if(!checkIfMainMethodIsValid(currentMethodObj)) {
				return;
			}
		}
		currentMethodReturnType = null;
		currentMethodObj = null;
		report_info("MethodDecl", methodDecl);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ FormParams ~~~~~~~~~~~~~~~~~~~
	
	public void visit(FormParamVar formParamVar) {
		if (checkIfSymbolExistsInCurrentScope(formParamVar.getParamName(), formParamVar)) {
			return;
		}
		Tab.insert(Obj.Var, formParamVar.getParamName(), currentType).setFpPos(FUNC_FORM_PARAM);
		report_info("FormParamVar", formParamVar);
	}
	
	public void visit(FormParamArray formParamArray) {
		if (checkIfSymbolExistsInCurrentScope(formParamArray.getParamName(), formParamArray)) {
			return;
		}
		Tab.insert(Obj.Var, formParamArray.getParamName(), new Struct(Struct.Array, currentType)).setFpPos(FUNC_FORM_PARAM);
		report_info("FormParamArray", formParamArray);
	}
		
	// ~~~~~~~~~~~~~~~~~~~ Type ~~~~~~~~~~~~~~~~~~~
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Type (" + type.getTypeName() + ") not found in the Symbol Table", type);
			type.struct = Tab.noType;
		} else {
			if (typeNode.getKind() == Obj.Type) {
				type.struct = typeNode.getType();
			} else {
				report_error("Type (" + type.getTypeName() + ") is not a valid type in the Symbol Table", type);
				type.struct = Tab.noType;
			}
		}
		currentType = type.struct;
		report_info("Type", type);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Designator Statement ~~~~~~~~~~~~~~~~~~~
		
	public void visit(DesignatorAssignopExpression designatorAssignopExpression) {
		Obj designatorObj = designatorAssignopExpression.getDesignator().obj;
		Struct exprStruct = designatorAssignopExpression.getExpr().struct;
		if (designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Elem && designatorObj.getKind() != Obj.Fld) {
			report_error("Designator " + designatorObj.getName() + " can't be assigned a value!", designatorAssignopExpression);
			return;
		}
		if (!checkIfTypeIsAssignableToGivenType(exprStruct, designatorObj.getType(), designatorAssignopExpression)) {
			return;
		}
		report_info("DesignatorAssignopExpression", designatorAssignopExpression);
	}
	
	public void visit(DesignatorStatementAssignArray designatorStatementAssignArray) {
		// TODO
	}
	
	// ~~~~~~~~~~~~~~~~~~~ DesignatorAction ~~~~~~~~~~~~~~~~~~~	
	
	public void visit(DesignatorActionMethodCall designatorActionMethodCall) {
		if (designatorActionMethodCall.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Designator is not a method!", designatorActionMethodCall);
			return;
		}
		if (!checkIfActParsAreMatchingWithCurrentActPars(designatorActionMethodCall.getDesignator().obj)) {
			report_error("ActPars doesn't match with FormPars for the Method!", designatorActionMethodCall);
			return;
		}
		report_info("DesignatorActionMethodCall", designatorActionMethodCall);
	}
	
	public void visit(DesignatorActionInc designatorActionInc) {
		Obj designatorObj = designatorActionInc.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Fld && designatorObj.getKind() != Obj.Elem) {
			report_error("Designator must be Var, Field or Elem for INC!", designatorActionInc);
			return;
		}
		if (designatorObj.getType() != Tab.intType) {
			report_error("Designator must have numeric type for INC!", designatorActionInc);
			return;
		}
		report_info("DesignatorActionInc", designatorActionInc);

	}
	
	public void visit(DesignatorActionDec designatorActionDec) {
		Obj designatorObj = designatorActionDec.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Fld && designatorObj.getKind() != Obj.Elem) {
			report_error("Designator must be Var, Field or Elem for DEC!", designatorActionDec);
			return;
		}
		if (designatorObj.getType() != Tab.intType) {
			report_error("Designator must have numeric type for DEC!", designatorActionDec);
			return;
		}
		report_info("DesignatorActionDec", designatorActionDec);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Designator ~~~~~~~~~~~~~~~~~~~	DONE
	
	public void visit(DesignatorSingle designatorSingle) {
		Obj designatorObj = Tab.find(designatorSingle.getDesignatorName());
		if (designatorObj == Tab.noObj) {
			report_error("Designator " + designatorSingle.getDesignatorName() + " was not declared!", designatorSingle);
			return;
		}
		designatorSingle.obj = designatorObj;
		report_info("DesignatorSingle", designatorSingle);
	}
	
	public void visit(DesignatorSquareBrackets designatorSquareBrackets) {
		Obj designatorObj = designatorSquareBrackets.getDesignator().obj;
		if (designatorSquareBrackets.getExpr().struct != Tab.intType) {
			report_error("Indexing designator expression must have numeric value!", designatorSquareBrackets);
			designatorSquareBrackets.obj = Tab.noObj;
			return;
		}
		if (designatorObj.getType().getKind() != Struct.Array) {
			report_error("Can't index non array designator!", designatorSquareBrackets);
			designatorSquareBrackets.obj = Tab.noObj;
			return;
		}
		Obj newDesignator = new Obj(Obj.Elem, designatorObj.getName(), designatorObj.getType().getElemType());
		designatorSquareBrackets.obj = newDesignator;
		report_info("DesignatorSquareBrackets", designatorSquareBrackets);
	}
	
	public void visit(DesignatorDot designatorDot) {
		// LEVEL C
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Statements ~~~~~~~~~~~~~~~~~~~
	
	public void visit(StatementWhileStart statementWhileStart) {
		nestedLoopCnt++;
		report_info("StatementWhileStart", statementWhileStart);
	}
	
	public void visit(StatementWhile statementWhile) {
		nestedLoopCnt--;
		report_info("StatementWhile", statementWhile);

	}
	
	public void visit(StatementBreak statementBreak) {
		if (nestedLoopCnt == 0) {
			report_error("Can't use break outside loops!", statementBreak);
			return;
		}
		report_info("StatementBreak", statementBreak);
	}
	
	public void visit(StatementContinue statementContinue) {
		if (nestedLoopCnt == 0) {
			report_error("Can't use continue outside loops!", statementContinue);
			return;
		}
		report_info("StatementContinue", statementContinue);
	}
	
	public void visit(StatementReturnExpr statementReturnExpr) {
		if (currentMethodReturnType == null) {
			report_error("Can't return outside method body!", statementReturnExpr);
			return;
		}
		if (statementReturnExpr.getExpr().struct != currentMethodReturnType) {
			report_error("Return type doesn't match currentMethodReturnType!", statementReturnExpr);
			return;
		}
		report_info("StatementReturnExpr", statementReturnExpr);
	}
	
	public void visit(StatementReturnVoid statementReturnVoid) {
		if (currentMethodReturnType == null) {
			report_error("Can't return outside method body!", statementReturnVoid);
			return;
		}
		if (currentMethodReturnType != Tab.noType) {
			report_error("Return type can't be empty!", statementReturnVoid);
			return;
		}
		report_info("StatementReturnVoid", statementReturnVoid);
	}
	
	public void visit(StatementRead statementRead) {
		Obj designatorObj = statementRead.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Var && designatorObj.getKind() != Obj.Fld && designatorObj.getKind() != Obj.Elem) {
			report_error("Designator must be Var, Fld or Elem for read statement!", statementRead);
			return;
		}
		if (designatorObj.getType() != Tab.intType && designatorObj.getType() != Tab.charType && designatorObj.getType() != TabWrapper.boolType) {
			report_error("Designator must have int, char or bool type for read statement!", statementRead);
			return;
		}
		report_info("StatementRead", statementRead);
	}
	
	public void visit(StatementPrint statementPrint) {
		Struct exprType = statementPrint.getExpr().struct;
		if (exprType != Tab.intType && exprType != Tab.charType && exprType != TabWrapper.boolType) {
			report_error("Expr must have int, char or bool type for print statement!", statementPrint);
			return;
		}
		report_info("StatementPrint", statementPrint);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Expr ~~~~~~~~~~~~~~~~~~~	DONE
	
	public void visit(ExprSingleMinus exprSingleMinus) {
		if (exprSingleMinus.getTerm().struct != Tab.intType) {
			report_error("Term must be a numberic value in order to have a Minus!", exprSingleMinus);
			exprSingleMinus.struct = Tab.noType;
			return;
		}
		exprSingleMinus.struct = exprSingleMinus.getTerm().struct;
		report_info("ExprSingleMinus", exprSingleMinus);
	}
	
	public void visit(ExprSingle exprSingle) {
		exprSingle.struct = exprSingle.getTerm().struct;
		report_info("ExprSingle", exprSingle);
	}
	
	public void visit(ExprMultiple exprMultiple) {
		if (exprMultiple.getTerm().struct != Tab.intType || exprMultiple.getExpr().struct != Tab.intType) {
			report_error("All terms must be numberic values in order to form ExprMultiple!", exprMultiple);
			exprMultiple.struct = Tab.noType;
			return;
		}
		exprMultiple.struct = exprMultiple.getExpr().struct;
		report_info("ExprMultiple", exprMultiple);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Term ~~~~~~~~~~~~~~~~~~~	DONE
	
	public void visit(TermSingle termSingle) {
		termSingle.struct = termSingle.getFactor().struct;
		report_info("TermSingle", termSingle);
	}
	
	public void visit(TermMultiple termMultiple) {
		if (termMultiple.getFactor().struct != Tab.intType || termMultiple.getTerm().struct != Tab.intType) {
			report_error("All factors must be numberic values in order to form TermMultiple!", termMultiple);
			termMultiple.struct = Tab.noType;
			return;
		}
		termMultiple.struct = termMultiple.getTerm().struct;
		report_info("TermMultiple", termMultiple);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Factor ~~~~~~~~~~~~~~~~~~~
	
	public void visit(FactorNumConst factorNumConst) {
		factorNumConst.struct = Tab.intType;
		report_info("FactorNumConst", factorNumConst);
	}
	
	public void visit(FactorCharConst factorCharConst) {
		factorCharConst.struct = Tab.charType;
		report_info("FactorCharConst", factorCharConst);
	}
	
	public void visit(FactorBoolConst factorBoolConst) {
		factorBoolConst.struct = TabWrapper.boolType;
		report_info("FactorBoolConst", factorBoolConst);
	}
	
	public void visit(FactorNewArray factorNewArray) {
		if (factorNewArray.getExpr().struct != Tab.intType) {
			report_error("New array must have numeric value for size!", factorNewArray);
			factorNewArray.struct = Tab.noType;
			return;
		}
		factorNewArray.struct = new Struct(Struct.Array, factorNewArray.getType().struct);
		report_info("FactorNewArray", factorNewArray);
	}
	
	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
		report_info("FactorExpr", factorExpr);
	}
	
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
		report_info("FactorDesignator", factorDesignator);
	}
	
	public void visit(FactorDesignatorMethodCall factorDesignatorMethodCall) {
		if (factorDesignatorMethodCall.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Designator is not a method!", factorDesignatorMethodCall);
			factorDesignatorMethodCall.struct = Tab.noType;
			return;
		}
		if (!checkIfActParsAreMatchingWithCurrentActPars(factorDesignatorMethodCall.getDesignator().obj)) {
			report_error("ActPars doesn't match with FormPars for the Method!", factorDesignatorMethodCall);
			factorDesignatorMethodCall.struct = Tab.noType;
			return;
		}
		factorDesignatorMethodCall.struct = factorDesignatorMethodCall.getDesignator().obj.getType();
		report_info("FactorDesignatorMethodCall", factorDesignatorMethodCall);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ ActPars ~~~~~~~~~~~~~~~~~~~
	
	public void visit(ActParsSingle actParsSingle) {
		currentActParsTypes = new ArrayList<>();
		currentActParsTypes.add(actParsSingle.getExpr().struct);
		report_info("ActParsSingle", actParsSingle);

	}

	public void visit(ActParsMultiple actParsMultiple) {
		currentActParsTypes.add(actParsMultiple.getExpr().struct);
		report_info("ActParsMultiple", actParsMultiple);
	}
	
}
