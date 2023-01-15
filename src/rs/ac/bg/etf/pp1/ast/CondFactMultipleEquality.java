// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:8:42


package rs.ac.bg.etf.pp1.ast;

public class CondFactMultipleEquality extends CondFact {

    private Expr Expr;
    private RelopEquality RelopEquality;
    private Expr Expr1;

    public CondFactMultipleEquality (Expr Expr, RelopEquality RelopEquality, Expr Expr1) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.RelopEquality=RelopEquality;
        if(RelopEquality!=null) RelopEquality.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public RelopEquality getRelopEquality() {
        return RelopEquality;
    }

    public void setRelopEquality(RelopEquality RelopEquality) {
        this.RelopEquality=RelopEquality;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(RelopEquality!=null) RelopEquality.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(RelopEquality!=null) RelopEquality.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(RelopEquality!=null) RelopEquality.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactMultipleEquality(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RelopEquality!=null)
            buffer.append(RelopEquality.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactMultipleEquality]");
        return buffer.toString();
    }
}
