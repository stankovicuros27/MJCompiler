// generated with ast extension for cup
// version 0.8
// 10/0/2023 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class MethodReturnTypeIsVoid extends MethodReturnType {

    public MethodReturnTypeIsVoid () {
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
        buffer.append("MethodReturnTypeIsVoid(\n");

        buffer.append(tab);
        buffer.append(") [MethodReturnTypeIsVoid]");
        return buffer.toString();
    }
}
