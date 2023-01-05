// generated with ast extension for cup
// version 0.8
// 5/0/2023 20:23:17


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclListEEmpty extends MethodDeclListE {

    public MethodDeclListEEmpty () {
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
        buffer.append("MethodDeclListEEmpty(\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclListEEmpty]");
        return buffer.toString();
    }
}
