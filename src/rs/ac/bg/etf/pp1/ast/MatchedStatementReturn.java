// generated with ast extension for cup
// version 0.8
// 3/0/2023 19:20:51


package rs.ac.bg.etf.pp1.ast;

public class MatchedStatementReturn extends MatchedStatement {

    private ExprOptional ExprOptional;

    public MatchedStatementReturn (ExprOptional ExprOptional) {
        this.ExprOptional=ExprOptional;
        if(ExprOptional!=null) ExprOptional.setParent(this);
    }

    public ExprOptional getExprOptional() {
        return ExprOptional;
    }

    public void setExprOptional(ExprOptional ExprOptional) {
        this.ExprOptional=ExprOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOptional!=null) ExprOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOptional!=null) ExprOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOptional!=null) ExprOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedStatementReturn(\n");

        if(ExprOptional!=null)
            buffer.append(ExprOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStatementReturn]");
        return buffer.toString();
    }
}
