// generated with ast extension for cup
// version 0.8
// 3/0/2023 17:26:13


package rs.ac.bg.etf.pp1.ast;

public class RelopLessEquals extends Relop {

    public RelopLessEquals () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelopLessEquals(\n");

        buffer.append(tab);
        buffer.append(") [RelopLessEquals]");
        return buffer.toString();
    }
}
