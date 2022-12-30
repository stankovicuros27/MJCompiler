// generated with ast extension for cup
// version 0.8
// 30/11/2022 23:8:13


package rs.ac.bg.etf.pp1.ast;

public class VarDeclInnerListMultiple extends VarDeclInnerList {

    private VarDeclInnerList VarDeclInnerList;
    private SquareBracketsOptional SquareBracketsOptional;

    public VarDeclInnerListMultiple (VarDeclInnerList VarDeclInnerList, SquareBracketsOptional SquareBracketsOptional) {
        this.VarDeclInnerList=VarDeclInnerList;
        if(VarDeclInnerList!=null) VarDeclInnerList.setParent(this);
        this.SquareBracketsOptional=SquareBracketsOptional;
        if(SquareBracketsOptional!=null) SquareBracketsOptional.setParent(this);
    }

    public VarDeclInnerList getVarDeclInnerList() {
        return VarDeclInnerList;
    }

    public void setVarDeclInnerList(VarDeclInnerList VarDeclInnerList) {
        this.VarDeclInnerList=VarDeclInnerList;
    }

    public SquareBracketsOptional getSquareBracketsOptional() {
        return SquareBracketsOptional;
    }

    public void setSquareBracketsOptional(SquareBracketsOptional SquareBracketsOptional) {
        this.SquareBracketsOptional=SquareBracketsOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclInnerList!=null) VarDeclInnerList.accept(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclInnerList!=null) VarDeclInnerList.traverseTopDown(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclInnerList!=null) VarDeclInnerList.traverseBottomUp(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseBottomUp(visitor);
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

        if(SquareBracketsOptional!=null)
            buffer.append(SquareBracketsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclInnerListMultiple]");
        return buffer.toString();
    }
}
