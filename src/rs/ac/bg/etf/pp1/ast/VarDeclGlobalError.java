// generated with ast extension for cup
// version 0.8
// 4/0/2023 0:11:2


package rs.ac.bg.etf.pp1.ast;

public class VarDeclGlobalError extends VarDeclGlobal {

    public VarDeclGlobalError () {
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
        buffer.append("VarDeclGlobalError(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclGlobalError]");
        return buffer.toString();
    }
}
