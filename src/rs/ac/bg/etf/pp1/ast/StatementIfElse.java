// generated with ast extension for cup
// version 0.8
// 10/0/2023 21:21:20


package rs.ac.bg.etf.pp1.ast;

public class StatementIfElse extends Statement {

    private IfStatement IfStatement;
    private ElseStatement ElseStatement;

    public StatementIfElse (IfStatement IfStatement, ElseStatement ElseStatement) {
        this.IfStatement=IfStatement;
        if(IfStatement!=null) IfStatement.setParent(this);
        this.ElseStatement=ElseStatement;
        if(ElseStatement!=null) ElseStatement.setParent(this);
    }

    public IfStatement getIfStatement() {
        return IfStatement;
    }

    public void setIfStatement(IfStatement IfStatement) {
        this.IfStatement=IfStatement;
    }

    public ElseStatement getElseStatement() {
        return ElseStatement;
    }

    public void setElseStatement(ElseStatement ElseStatement) {
        this.ElseStatement=ElseStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStatement!=null) IfStatement.accept(visitor);
        if(ElseStatement!=null) ElseStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStatement!=null) IfStatement.traverseTopDown(visitor);
        if(ElseStatement!=null) ElseStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStatement!=null) IfStatement.traverseBottomUp(visitor);
        if(ElseStatement!=null) ElseStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIfElse(\n");

        if(IfStatement!=null)
            buffer.append(IfStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStatement!=null)
            buffer.append(ElseStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIfElse]");
        return buffer.toString();
    }
}
