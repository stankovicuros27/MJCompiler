package rs.ac.bg.etf.pp1;

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
    
    private Struct currentType = null;
    private Struct currentMethodReturnType = null;
    private Obj currentMethodObj = null;
    
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
        
	// ~~~~~~~~~~~~~~~~~~~ Program ~~~~~~~~~~~~~~~~~~~
    
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
		Tab.openScope();
		report_info("ProgramName", programName);
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
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
		Tab.insert(Obj.Var, varDeclInnerListElementVar.getVarName(), currentType);
		report_info("VarDeclInnerListElementVar", varDeclInnerListElementVar);
	}
	
	public void visit(VarDeclInnerListElementArray varDeclInnerListElementArray) {
		if (checkIfSymbolExistsInCurrentScope(varDeclInnerListElementArray.getVarName(), varDeclInnerListElementArray)) {
			return;
		}
		Tab.insert(Obj.Var, varDeclInnerListElementArray.getVarName(), new Struct(Struct.Array, currentType));
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
		currentMethodReturnType = null;
		currentMethodObj = null;
		report_info("MethodDecl", methodDecl);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ FormParams ~~~~~~~~~~~~~~~~~~~
	
	public void visit(FormParamVar formParamVar) {
		if (checkIfSymbolExistsInCurrentScope(formParamVar.getParamName(), formParamVar)) {
			return;
		}
		Obj formParamVarObj = Tab.insert(Obj.Var, formParamVar.getParamName(), currentType);
		report_info("FormParamVar", formParamVar);
	}
	
	public void visit(FormParamArray formParamArray) {
		if (checkIfSymbolExistsInCurrentScope(formParamArray.getParamName(), formParamArray)) {
			return;
		}
		Obj formParamArrObj = Tab.insert(Obj.Var, formParamArray.getParamName(), new Struct(Struct.Array, currentType));
		report_info("FormParamArray", formParamArray);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Method Return ~~~~~~~~~~~~~~~~~~~
	
	//public void visit(MatchedStatementReturn matchedStatementReturn) {
		
	//}
	
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
	
	// ~~~~~~~~~~~~~~~~~~~ Designator ~~~~~~~~~~~~~~~~~~~
	
	public void visit(DesignatorSingle designatorSingle) {
		Obj designatorObj = Tab.find(designatorSingle.getDesignatorName());
		if (designatorObj == Tab.noObj) {
			report_error("Designator " + designatorSingle.getDesignatorName() + " was not declared!", designatorSingle);
			return;
		}
		designatorSingle.obj = designatorObj;
		report_info("DesignatorSingle", designatorSingle);
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Expr ~~~~~~~~~~~~~~~~~~~
	
	public void visit(ExprSingleMinus exprSingleMinus) {
		// TODO
	}
	
	public void visit(ExprSingle exprSingle) {
		exprSingle.struct = exprSingle.getTerm().struct;
		report_info("ExprSingle", exprSingle);
	}
	
	public void visit(ExprMultiple exprMultiple) {
		// TODO
	}
	
	// ~~~~~~~~~~~~~~~~~~~ Term ~~~~~~~~~~~~~~~~~~~
	
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
	
	
	public void visit(FactorNew factorNew) {
		// TODO obj and array
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
		// TODO
	}
	
}
