// generated with ast extension for cup
// version 0.8
// 9/0/2023 21:5:42


package rs.ac.bg.etf.pp1.ast;

public class RelopTypeComparaison extends Relop {

    private RelopComparison RelopComparison;

    public RelopTypeComparaison (RelopComparison RelopComparison) {
        this.RelopComparison=RelopComparison;
        if(RelopComparison!=null) RelopComparison.setParent(this);
    }

    public RelopComparison getRelopComparison() {
        return RelopComparison;
    }

    public void setRelopComparison(RelopComparison RelopComparison) {
        this.RelopComparison=RelopComparison;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RelopComparison!=null) RelopComparison.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RelopComparison!=null) RelopComparison.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RelopComparison!=null) RelopComparison.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelopTypeComparaison(\n");

        if(RelopComparison!=null)
            buffer.append(RelopComparison.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelopTypeComparaison]");
        return buffer.toString();
    }
}
