// generated with ast extension for cup
// version 0.8
// 3/0/2023 19:20:51


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclInnerListMultiple extends ConstDeclInnerList {

    private ConstDeclInnerList ConstDeclInnerList;
    private ConstDeclInnerListElement ConstDeclInnerListElement;

    public ConstDeclInnerListMultiple (ConstDeclInnerList ConstDeclInnerList, ConstDeclInnerListElement ConstDeclInnerListElement) {
        this.ConstDeclInnerList=ConstDeclInnerList;
        if(ConstDeclInnerList!=null) ConstDeclInnerList.setParent(this);
        this.ConstDeclInnerListElement=ConstDeclInnerListElement;
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.setParent(this);
    }

    public ConstDeclInnerList getConstDeclInnerList() {
        return ConstDeclInnerList;
    }

    public void setConstDeclInnerList(ConstDeclInnerList ConstDeclInnerList) {
        this.ConstDeclInnerList=ConstDeclInnerList;
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
        if(ConstDeclInnerList!=null) ConstDeclInnerList.accept(visitor);
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclInnerList!=null) ConstDeclInnerList.traverseTopDown(visitor);
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclInnerList!=null) ConstDeclInnerList.traverseBottomUp(visitor);
        if(ConstDeclInnerListElement!=null) ConstDeclInnerListElement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclInnerListMultiple(\n");

        if(ConstDeclInnerList!=null)
            buffer.append(ConstDeclInnerList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclInnerListElement!=null)
            buffer.append(ConstDeclInnerListElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclInnerListMultiple]");
        return buffer.toString();
    }
}
