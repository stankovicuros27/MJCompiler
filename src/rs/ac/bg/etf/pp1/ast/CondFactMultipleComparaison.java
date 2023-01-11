// generated with ast extension for cup
// version 0.8
// 11/0/2023 17:16:27


package rs.ac.bg.etf.pp1.ast;

public class CondFactMultipleComparaison extends CondFact {

    private Expr Expr;
    private RelopComparison RelopComparison;
    private Expr Expr1;

    public CondFactMultipleComparaison (Expr Expr, RelopComparison RelopComparison, Expr Expr1) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.RelopComparison=RelopComparison;
        if(RelopComparison!=null) RelopComparison.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public RelopComparison getRelopComparison() {
        return RelopComparison;
    }

    public void setRelopComparison(RelopComparison RelopComparison) {
        this.RelopComparison=RelopComparison;
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
        if(RelopComparison!=null) RelopComparison.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(RelopComparison!=null) RelopComparison.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(RelopComparison!=null) RelopComparison.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactMultipleComparaison(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RelopComparison!=null)
            buffer.append(RelopComparison.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactMultipleComparaison]");
        return buffer.toString();
    }
}
