// generated with ast extension for cup
// version 0.8
// 10/0/2023 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class VarDeclInnerListSingle extends VarDeclInnerList {

    private VarDeclInnerListElement VarDeclInnerListElement;

    public VarDeclInnerListSingle (VarDeclInnerListElement VarDeclInnerListElement) {
        this.VarDeclInnerListElement=VarDeclInnerListElement;
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.setParent(this);
    }

    public VarDeclInnerListElement getVarDeclInnerListElement() {
        return VarDeclInnerListElement;
    }

    public void setVarDeclInnerListElement(VarDeclInnerListElement VarDeclInnerListElement) {
        this.VarDeclInnerListElement=VarDeclInnerListElement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclInnerListSingle(\n");

        if(VarDeclInnerListElement!=null)
            buffer.append(VarDeclInnerListElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclInnerListSingle]");
        return buffer.toString();
    }
}
