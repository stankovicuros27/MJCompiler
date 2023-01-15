// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:41:32


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(FormPars FormPars);
    public void visit(Factor Factor);
    public void visit(Statement Statement);
    public void visit(ParenActParsOptional ParenActParsOptional);
    public void visit(RelopExprOptional RelopExprOptional);
    public void visit(DesignatorAction DesignatorAction);
    public void visit(VarDeclInnerList VarDeclInnerList);
    public void visit(Relop Relop);
    public void visit(ClassConstructorsAndMethodsDeclOptional ClassConstructorsAndMethodsDeclOptional);
    public void visit(VarDeclGlobal VarDeclGlobal);
    public void visit(Expr Expr);
    public void visit(DesignatorAssignopExpr DesignatorAssignopExpr);
    public void visit(UnmatchedStatement UnmatchedStatement);
    public void visit(ConstructorDeclListE ConstructorDeclListE);
    public void visit(ExtendsTypeOptional ExtendsTypeOptional);
    public void visit(NewTypeSuffix NewTypeSuffix);
    public void visit(TermAddopList TermAddopList);
    public void visit(DesignatorList DesignatorList);
    public void visit(Condition Condition);
    public void visit(Const Const);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(PrintNumConstOptional PrintNumConstOptional);
    public void visit(Addop Addop);
    public void visit(ActParsOptional ActParsOptional);
    public void visit(SquareBracketsOptional SquareBracketsOptional);
    public void visit(CondTerm CondTerm);
    public void visit(ConditionInIf ConditionInIf);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(MethodDeclListE MethodDeclListE);
    public void visit(DesignatorOptional DesignatorOptional);
    public void visit(FormParam FormParam);
    public void visit(ExprOptional ExprOptional);
    public void visit(ActPars ActPars);
    public void visit(DesignatorListOptionalES DesignatorListOptionalES);
    public void visit(Designator Designator);
    public void visit(VarDeclInnerListElement VarDeclInnerListElement);
    public void visit(ProgramDeclListE ProgramDeclListE);
    public void visit(CondFact CondFact);
    public void visit(ConstDeclInnerList ConstDeclInnerList);
    public void visit(ArrMaxVarDesignator ArrMaxVarDesignator);
    public void visit(VarDeclInnerListGlobal VarDeclInnerListGlobal);
    public void visit(FormParsOptional FormParsOptional);
    public void visit(Term Term);
    public void visit(RelopEquality RelopEquality);
    public void visit(RelopComparison RelopComparison);
    public void visit(VarDeclListE VarDeclListE);
    public void visit(StatementListE StatementListE);
    public void visit(MethodReturnType MethodReturnType);
    public void visit(ArrSortArrDesignator ArrSortArrDesignator);
    public void visit(ArrMaxArrDesignator ArrMaxArrDesignator);
    public void visit(AddopMod AddopMod);
    public void visit(AddopDiv AddopDiv);
    public void visit(AddopMul AddopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLessEquals RelopLessEquals);
    public void visit(RelopLess RelopLess);
    public void visit(RelopGreaterEquals RelopGreaterEquals);
    public void visit(RelopGreater RelopGreater);
    public void visit(RelopNotEquals RelopNotEquals);
    public void visit(RelopEquals RelopEquals);
    public void visit(RelopTypeComparaison RelopTypeComparaison);
    public void visit(RelopTypeEquality RelopTypeEquality);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(DesignatorSingle DesignatorSingle);
    public void visit(DesignatorSquareBrackets DesignatorSquareBrackets);
    public void visit(DesignatorDot DesignatorDot);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNewArray FactorNewArray);
    public void visit(FactorBoolConst FactorBoolConst);
    public void visit(FactorCharConst FactorCharConst);
    public void visit(FactorNumConst FactorNumConst);
    public void visit(FactorDesignatorMethodCall FactorDesignatorMethodCall);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(TermSingle TermSingle);
    public void visit(TermMultiple TermMultiple);
    public void visit(ExprMultiple ExprMultiple);
    public void visit(ExprSingle ExprSingle);
    public void visit(ExprSingleMinus ExprSingleMinus);
    public void visit(CondFactMultipleComparaison CondFactMultipleComparaison);
    public void visit(CondFactMultipleEquality CondFactMultipleEquality);
    public void visit(CondFactSingle CondFactSingle);
    public void visit(PlaceAfterFirstConditionInAnd PlaceAfterFirstConditionInAnd);
    public void visit(CondTermSingle CondTermSingle);
    public void visit(CondTermMultiple CondTermMultiple);
    public void visit(PlaceAfterFirstConditionInOr PlaceAfterFirstConditionInOr);
    public void visit(ConditionSingle ConditionSingle);
    public void visit(ConditionMultiple ConditionMultiple);
    public void visit(ConditionInIfError ConditionInIfError);
    public void visit(ConditionInIfExists ConditionInIfExists);
    public void visit(ActParsOptionalEmpty ActParsOptionalEmpty);
    public void visit(ActParsOptionalExist ActParsOptionalExist);
    public void visit(ActParsSingle ActParsSingle);
    public void visit(ActParsMultiple ActParsMultiple);
    public void visit(DesignatorAssignopExprError DesignatorAssignopExprError);
    public void visit(DesignatorAssignopExpression DesignatorAssignopExpression);
    public void visit(DesignatorActionDec DesignatorActionDec);
    public void visit(DesignatorActionInc DesignatorActionInc);
    public void visit(DesignatorActionMethodCall DesignatorActionMethodCall);
    public void visit(DesignatorOptionalEmpty DesignatorOptionalEmpty);
    public void visit(DesignatorOptionalExist DesignatorOptionalExist);
    public void visit(DesignatorListOptionalESEmpty DesignatorListOptionalESEmpty);
    public void visit(DesignatorListOptionalESExist DesignatorListOptionalESExist);
    public void visit(DesignatorStatementAssignArrayStart DesignatorStatementAssignArrayStart);
    public void visit(DesignatorStatementAssignArray DesignatorStatementAssignArray);
    public void visit(DesignatorStatementAction DesignatorStatementAction);
    public void visit(DesignatorStatementAssign DesignatorStatementAssign);
    public void visit(PrintNumConstOptionalEmpty PrintNumConstOptionalEmpty);
    public void visit(PrintNumConstOptionalExists PrintNumConstOptionalExists);
    public void visit(ForeachVarDesignator ForeachVarDesignator);
    public void visit(ForeachStatementBeginning ForeachStatementBeginning);
    public void visit(ForeachStatement ForeachStatement);
    public void visit(PlaceAfterElseBlock PlaceAfterElseBlock);
    public void visit(ElseStatementStart ElseStatementStart);
    public void visit(ElseStatement ElseStatement);
    public void visit(PlaceAfterIfBlock PlaceAfterIfBlock);
    public void visit(PlaceAfterIfCondition PlaceAfterIfCondition);
    public void visit(IfStatementStart IfStatementStart);
    public void visit(IfStatement IfStatement);
    public void visit(StatementWhileStart StatementWhileStart);
    public void visit(StatementGotoLabel StatementGotoLabel);
    public void visit(StatementLabelDeclare StatementLabelDeclare);
    public void visit(StatementArrSort StatementArrSort);
    public void visit(StatementArrMax StatementArrMax);
    public void visit(StatementSection StatementSection);
    public void visit(StatementPrint StatementPrint);
    public void visit(StatementRead StatementRead);
    public void visit(StatementReturnVoid StatementReturnVoid);
    public void visit(StatementReturnExpr StatementReturnExpr);
    public void visit(StatementForeach StatementForeach);
    public void visit(StatementContinue StatementContinue);
    public void visit(StatementBreak StatementBreak);
    public void visit(StatementWhile StatementWhile);
    public void visit(StatementIf StatementIf);
    public void visit(StatementIfElse StatementIfElse);
    public void visit(StatementDesignator StatementDesignator);
    public void visit(StatementListEEmpty StatementListEEmpty);
    public void visit(StatementListEExists StatementListEExists);
    public void visit(Type Type);
    public void visit(FormParamArray FormParamArray);
    public void visit(FormParamVar FormParamVar);
    public void visit(FormParsError FormParsError);
    public void visit(FormParsMultiple FormParsMultiple);
    public void visit(FormParsSingle FormParsSingle);
    public void visit(FormParsOptionalEmpty FormParsOptionalEmpty);
    public void visit(FormParsOptionalExists FormParsOptionalExists);
    public void visit(MethodReturnTypeIsVoid MethodReturnTypeIsVoid);
    public void visit(MethodReturnTypeHasType MethodReturnTypeHasType);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(MethodDeclListEEmpty MethodDeclListEEmpty);
    public void visit(MethodDeclListEExists MethodDeclListEExists);
    public void visit(ClassConstructorsAndMethodsDeclOptionalEmpty ClassConstructorsAndMethodsDeclOptionalEmpty);
    public void visit(ClassConstructorsAndMethodsDeclOptionalExists ClassConstructorsAndMethodsDeclOptionalExists);
    public void visit(ClassBody ClassBody);
    public void visit(ExtendsTypeOptionalError ExtendsTypeOptionalError);
    public void visit(ExtendsTypeOptionalEmpty ExtendsTypeOptionalEmpty);
    public void visit(ExtendsTypeOptionalExists ExtendsTypeOptionalExists);
    public void visit(ClassName ClassName);
    public void visit(ClassDecl ClassDecl);
    public void visit(VarDeclListEEmpty VarDeclListEEmpty);
    public void visit(VarDeclListEExists VarDeclListEExists);
    public void visit(VarDeclInnerListElementArray VarDeclInnerListElementArray);
    public void visit(VarDeclInnerListElementVar VarDeclInnerListElementVar);
    public void visit(VarDeclInnerListMultiple VarDeclInnerListMultiple);
    public void visit(VarDeclInnerListSingle VarDeclInnerListSingle);
    public void visit(VarDecl VarDecl);
    public void visit(VarDeclGlobalError VarDeclGlobalError);
    public void visit(VarDeclGlobalExists VarDeclGlobalExists);
    public void visit(ConstChar ConstChar);
    public void visit(ConstBool ConstBool);
    public void visit(ConstNum ConstNum);
    public void visit(ConstDeclInnerListElement ConstDeclInnerListElement);
    public void visit(ConstDeclInnerListMultiple ConstDeclInnerListMultiple);
    public void visit(ConstDeclInnerListSingle ConstDeclInnerListSingle);
    public void visit(ConstDecl ConstDecl);
    public void visit(ProgramDeclListEEmpty ProgramDeclListEEmpty);
    public void visit(ProgramDeclListEClass ProgramDeclListEClass);
    public void visit(ProgramDeclListEVar ProgramDeclListEVar);
    public void visit(ProgramDeclListEConst ProgramDeclListEConst);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
