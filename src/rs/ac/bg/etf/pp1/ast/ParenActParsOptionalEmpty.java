// generated with ast extension for cup
// version 0.8
// 30/11/2022 23:8:13


package rs.ac.bg.etf.pp1.ast;

public class ParenActParsOptionalEmpty extends ParenActParsOptional {

    public ParenActParsOptionalEmpty () {
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
        buffer.append("ParenActParsOptionalEmpty(\n");

        buffer.append(tab);
        buffer.append(") [ParenActParsOptionalEmpty]");
        return buffer.toString();
    }
}
