// generated with ast extension for cup
// version 0.8
// 2/0/2023 17:7:52


package rs.ac.bg.etf.pp1.ast;

public class MatchedStatementForeach extends MatchedStatement {

    private ForeachStatement ForeachStatement;

    public MatchedStatementForeach (ForeachStatement ForeachStatement) {
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
        buffer.append("MatchedStatementForeach(\n");

        if(ForeachStatement!=null)
            buffer.append(ForeachStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStatementForeach]");
        return buffer.toString();
    }
}
