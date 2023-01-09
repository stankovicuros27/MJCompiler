// generated with ast extension for cup
// version 0.8
// 9/0/2023 21:5:42


package rs.ac.bg.etf.pp1.ast;

public class StatementForeach extends Statement {

    private ForeachStatement ForeachStatement;

    public StatementForeach (ForeachStatement ForeachStatement) {
        this.ForeachStatement=ForeachStatement;
        if(ForeachStatement!=null) ForeachStatement.setParent(this);
    }

    public ForeachStatement getForeachStatement() {
        return ForeachStatement;
    }

    public void setForeachStatement(ForeachStatement ForeachStatement) {
        this.ForeachStatement=ForeachStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForeachStatement!=null) ForeachStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachStatement!=null) ForeachStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachStatement!=null) ForeachStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementForeach(\n");

        if(ForeachStatement!=null)
            buffer.append(ForeachStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementForeach]");
        return buffer.toString();
    }
}
