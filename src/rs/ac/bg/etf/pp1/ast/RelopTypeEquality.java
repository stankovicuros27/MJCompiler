// generated with ast extension for cup
// version 0.8
// 7/0/2023 17:25:11


package rs.ac.bg.etf.pp1.ast;

public class RelopTypeEquality extends Relop {

    private RelopEquality RelopEquality;

    public RelopTypeEquality (RelopEquality RelopEquality) {
        this.RelopEquality=RelopEquality;
        if(RelopEquality!=null) RelopEquality.setParent(this);
    }

    public RelopEquality getRelopEquality() {
        return RelopEquality;
    }

    public void setRelopEquality(RelopEquality RelopEquality) {
        this.RelopEquality=RelopEquality;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RelopEquality!=null) RelopEquality.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RelopEquality!=null) RelopEquality.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RelopEquality!=null) RelopEquality.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelopTypeEquality(\n");

        if(RelopEquality!=null)
            buffer.append(RelopEquality.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RelopTypeEquality]");
        return buffer.toString();
    }
}
