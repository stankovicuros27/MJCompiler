// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:34:55


package rs.ac.bg.etf.pp1.ast;

public class RelopTypeComparaison extends Relop {

    private RelopComparaison RelopComparaison;

    public RelopTypeComparaison (RelopComparaison RelopComparaison) {
        this.RelopComparaison=RelopComparaison;
        if(RelopComparaison!=null) RelopComparaison.setParent(this);
    }

    public RelopComparaison getRelopComparaison() {
        return RelopComparaison;
    }

    public void setRelopComparaison(RelopComparaison RelopComparaison) {
        this.RelopComparaison=RelopComparaison;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RelopComparaison!=null) RelopComparaison.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RelopComparaison!=null) RelopComparaison.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RelopComparaison!=null) RelopComparaison.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelopTypeComparaison(\n");

        if(RelopComparaison!=null)
            buffer.append(RelopComparaison.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelopTypeComparaison]");
        return buffer.toString();
    }
}
