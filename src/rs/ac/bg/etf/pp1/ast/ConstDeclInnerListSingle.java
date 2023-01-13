// generated with ast extension for cup
// version 0.8
// 13/0/2023 16:9:0


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclInnerListSingle extends ConstDeclInnerList {

    private ConstDeclInnerListElement ConstDeclInnerListElement;

    public ConstDeclInnerListSingle (ConstDeclInnerListElement ConstDeclInnerListElement) {
        this.ConstDeclInnerListElement=ConstDeclInnerListElement;
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.setParent(this);
    }

    public ConstDeclInnerListElement getConstDeclInnerListElement() {
        return ConstDeclInnerListElement;
    }

    public void setConstDeclInnerListElement(ConstDeclInnerListElement ConstDeclInnerListElement) {
        this.ConstDeclInnerListElement=ConstDeclInnerListElement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclInnerListSingle(\n");

        if(ConstDeclInnerListElement!=null)
            buffer.append(ConstDeclInnerListElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclInnerListSingle]");
        return buffer.toString();
    }
}
