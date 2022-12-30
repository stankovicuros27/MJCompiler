// generated with ast extension for cup
// version 0.8
// 30/11/2022 18:23:9


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(ActParsOptional ActParsOptional);
    public void visit(ClassConstructorsAndMethodsDeclOptional ClassConstructorsAndMethodsDeclOptional);
    public void visit(ExprOptional ExprOptional);
    public void visit(Mulop Mulop);
    public void visit(VarDeclInnerList VarDeclInnerList);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(Relop Relop);
    public void visit(DesignatorAction DesignatorAction);
    public void visit(RelopExprOptional RelopExprOptional);
    public void visit(Addop Addop);
    public void visit(ConstructorDeclListE ConstructorDeclListE);
    public void visit(PrintNumConstOptional PrintNumConstOptional);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(ParenActParsOptional ParenActParsOptional);
    public void visit(TermAddopList TermAddopList);
    public void visit(Designator Designator);
    public void visit(NewTypeSuffix NewTypeSuffix);
    public void visit(ConstDeclInnerList ConstDeclInnerList);
    public void visit(Term Term);
    public void visit(ProgramDeclListE ProgramDeclListE);
    public void visit(Condition Condition);
    public void visit(FormParsOptional FormParsOptional);
    public void visit(MethodDeclListE MethodDeclListE);
    public void visit(VarDeclListE VarDeclListE);
    public void visit(StatementListE StatementListE);
    public void visit(ActPars ActPars);
    public void visit(ExtendsTypeOptional ExtendsTypeOptional);
    public void visit(DesignatorList DesignatorList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(UnmatchedStatement UnmatchedStatement);
    public void visit(MethodReturnType MethodReturnType);
    public void visit(Statement Statement);
    public void visit(SquareBracketsOptional SquareBracketsOptional);
    public void visit(OptionalMinus OptionalMinus);
    public void visit(FormPars FormPars);
    public void visit(DesignatorListOptionalES DesignatorListOptionalES);
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
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(DesignatorSingle DesignatorSingle);
    public void visit(DesignatorMultipleExpr DesignatorMultipleExpr);
    public void visit(DesignatorMultipleIdent DesignatorMultipleIdent);
    public void visit(NewTypeSuffixActPars NewTypeSuffixActPars);
    public void visit(NewTypeSuffixSquareBracket NewTypeSuffixSquareBracket);
    public void visit(ParenActParsOptionalEmpty ParenActParsOptionalEmpty);
    public void visit(ParenActParsOptionalExist ParenActParsOptionalExist);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNew FactorNew);
    public void visit(FactorBoolConst FactorBoolConst);
    public void visit(FactorCharConst FactorCharConst);
    public void visit(FactorNumConst FactorNumConst);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(TermSingle TermSingle);
    public void visit(TermMultiple TermMultiple);
    public void visit(TermAddopListSingle TermAddopListSingle);
    public void visit(TermAddopListMultiple TermAddopListMultiple);
    public void visit(OptionalMinusEmpty OptionalMinusEmpty);
    public void visit(OptionalMinusExist OptionalMinusExist);
    public void visit(Expr Expr);
    public void visit(RelopExprOptionalEmpty RelopExprOptionalEmpty);
    public void visit(RelopExprOptionalExist RelopExprOptionalExist);
    public void visit(CondFact CondFact);
    public void visit(CondTermSingle CondTermSingle);
    public void visit(CondTermMultiple CondTermMultiple);
    public void visit(ConditionSingle ConditionSingle);
    public void visit(ConditionMultiple ConditionMultiple);
    public void visit(ActParsOptionalEmpty ActParsOptionalEmpty);
    public void visit(ActParsOptionalExist ActParsOptionalExist);
    public void visit(ActParsSingle ActParsSingle);
    public void visit(ActParsMultiple ActParsMultiple);
    public void visit(DesignatorListSingle DesignatorListSingle);
    public void visit(DesignatorListMultiple DesignatorListMultiple);
    public void visit(DesignatorListOptionalESEmpty DesignatorListOptionalESEmpty);
    public void visit(DesignatorListOptionalESExist DesignatorListOptionalESExist);
    public void visit(DesignatorActionDec DesignatorActionDec);
    public void visit(DesignatorActionInc DesignatorActionInc);
    public void visit(DesignatorActionActPars DesignatorActionActPars);
    public void visit(DesignatorActionAssignop DesignatorActionAssignop);
    public void visit(DesignatorStatementSquareBracket DesignatorStatementSquareBracket);
    public void visit(DesignatorStatementAction DesignatorStatementAction);
    public void visit(PrintNumConstOptionalEmpty PrintNumConstOptionalEmpty);
    public void visit(PrintNumConstOptionalExists PrintNumConstOptionalExists);
    public void visit(ExprOptionalEmpty ExprOptionalEmpty);
    public void visit(ExprOptionalExists ExprOptionalExists);
    public void visit(MatchedStatementSection MatchedStatementSection);
    public void visit(MatchedStatementPrint MatchedStatementPrint);
    public void visit(MatchedStatementRead MatchedStatementRead);
    public void visit(MatchedStatementExpr MatchedStatementExpr);
    public void visit(MatchedStatementContinue MatchedStatementContinue);
    public void visit(MatchedStatementBreak MatchedStatementBreak);
    public void visit(MatchedStatementWhile MatchedStatementWhile);
    public void visit(MatchedStatementIfElse MatchedStatementIfElse);
    public void visit(MatchedStatementDesignator MatchedStatementDesignator);
    public void visit(UnmatchedStatementIfElse UnmatchedStatementIfElse);
    public void visit(UnmatchedStatementIf UnmatchedStatementIf);
    public void visit(StatementUnmatched StatementUnmatched);
    public void visit(StatementMatched StatementMatched);
    public void visit(Type Type);
    public void visit(FormParsMultiple FormParsMultiple);
    public void visit(FormParsSignle FormParsSignle);
    public void visit(FormParsEmpty FormParsEmpty);
    public void visit(FormParsExists FormParsExists);
    public void visit(MethodReturnTypeIsVoid MethodReturnTypeIsVoid);
    public void visit(MethodReturnTypeHasType MethodReturnTypeHasType);
    public void visit(MethodDecl MethodDecl);
    public void visit(MethodDeclListEEmpty MethodDeclListEEmpty);
    public void visit(MethodDeclListEExists MethodDeclListEExists);
    public void visit(ClassConstructorsAndMethodsDeclOptionalEmpty ClassConstructorsAndMethodsDeclOptionalEmpty);
    public void visit(ClassConstructorsAndMethodsDeclOptionalExists ClassConstructorsAndMethodsDeclOptionalExists);
    public void visit(VarDeclListEEmpty VarDeclListEEmpty);
    public void visit(VarDeclListEExists VarDeclListEExists);
    public void visit(ClassBody ClassBody);
    public void visit(ExtendsTypeEmpty ExtendsTypeEmpty);
    public void visit(ExtendsTypeExists ExtendsTypeExists);
    public void visit(ClassDecl ClassDecl);
    public void visit(SquareBracketsEmpty SquareBracketsEmpty);
    public void visit(SquareBracketsExists SquareBracketsExists);
    public void visit(VarDeclInnerListMultiple VarDeclInnerListMultiple);
    public void visit(VarDeclInnerListSingle VarDeclInnerListSingle);
    public void visit(VarDecl VarDecl);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(ConstDeclInnerListMultiple ConstDeclInnerListMultiple);
    public void visit(ConstDeclInnerListSingle ConstDeclInnerListSingle);
    public void visit(ConstDecl ConstDecl);
    public void visit(ProgramDeclListEEmpty ProgramDeclListEEmpty);
    public void visit(ProgramDeclListEClass ProgramDeclListEClass);
    public void visit(ProgramDeclListEVar ProgramDeclListEVar);
    public void visit(ProgramDeclListEConst ProgramDeclListEConst);
    public void visit(Program Program);

}
