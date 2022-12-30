// generated with ast extension for cup
// version 0.8
// 30/11/2022 18:23:9


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
