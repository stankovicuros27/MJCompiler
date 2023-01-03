// generated with ast extension for cup
// version 0.8
// 3/0/2023 19:20:51


package rs.ac.bg.etf.pp1.ast;

public class MatchedStatementIfElse extends MatchedStatement {

    private ConditionInIf ConditionInIf;
    private MatchedStatement MatchedStatement;
    private MatchedStatement MatchedStatement1;

    public MatchedStatementIfElse (ConditionInIf ConditionInIf, MatchedStatement MatchedStatement, MatchedStatement MatchedStatement1) {
        this.ConditionInIf=ConditionInIf;
        if(ConditionInIf!=null) ConditionInIf.setParent(this);
        this.MatchedStatement=MatchedStatement;
        if(MatchedStatement!=null) MatchedStatement.setParent(this);
        this.MatchedStatement1=MatchedStatement1;
        if(MatchedStatement1!=null) MatchedStatement1.setParent(this);
    }

    public ConditionInIf getConditionInIf() {
        return ConditionInIf;
    }

    public void setConditionInIf(ConditionInIf ConditionInIf) {
        this.ConditionInIf=ConditionInIf;
    }

    public MatchedStatement getMatchedStatement() {
        return MatchedStatement;
    }

    public void setMatchedStatement(MatchedStatement MatchedStatement) {
        this.MatchedStatement=MatchedStatement;
    }

    public MatchedStatement getMatchedStatement1() {
        return MatchedStatement1;
    }

    public void setMatchedStatement1(MatchedStatement MatchedStatement1) {
        this.MatchedStatement1=MatchedStatement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionInIf!=null) ConditionInIf.accept(visitor);
        if(MatchedStatement!=null) MatchedStatement.accept(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionInIf!=null) ConditionInIf.traverseTopDown(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseTopDown(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionInIf!=null) ConditionInIf.traverseBottomUp(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseBottomUp(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedStatementIfElse(\n");

        if(ConditionInIf!=null)
            buffer.append(ConditionInIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement!=null)
            buffer.append(MatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement1!=null)
            buffer.append(MatchedStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStatementIfElse]");
        return buffer.toString();
    }
}
