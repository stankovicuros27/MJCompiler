// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:41:32


package rs.ac.bg.etf.pp1.ast;

public class VarDeclInnerListMultiple extends VarDeclInnerList {

    private VarDeclInnerList VarDeclInnerList;
    private VarDeclInnerListElement VarDeclInnerListElement;

    public VarDeclInnerListMultiple (VarDeclInnerList VarDeclInnerList, VarDeclInnerListElement VarDeclInnerListElement) {
        this.VarDeclInnerList=VarDeclInnerList;
        if(VarDeclInnerList!=null) VarDeclInnerList.setParent(this);
        this.VarDeclInnerListElement=VarDeclInnerListElement;
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.setParent(this);
    }

    public VarDeclInnerList getVarDeclInnerList() {
        return VarDeclInnerList;
    }

    public void setVarDeclInnerList(VarDeclInnerList VarDeclInnerList) {
        this.VarDeclInnerList=VarDeclInnerList;
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
        if(VarDeclInnerList!=null) VarDeclInnerList.accept(visitor);
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclInnerList!=null) VarDeclInnerList.traverseTopDown(visitor);
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclInnerList!=null) VarDeclInnerList.traverseBottomUp(visitor);
        if(VarDeclInnerListElement!=null) VarDeclInnerListElement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclInnerListMultiple(\n");

        if(VarDeclInnerList!=null)
            buffer.append(VarDeclInnerList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclInnerListElement!=null)
            buffer.append(VarDeclInnerListElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclInnerListMultiple]");
        return buffer.toString();
    }
}
