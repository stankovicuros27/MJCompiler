// generated with ast extension for cup
// version 0.8
// 2/0/2023 17:7:52


package rs.ac.bg.etf.pp1.ast;

public class VarDeclInnerListElementDerived1 extends VarDeclInnerListElement {

    private SquareBracketsOptional SquareBracketsOptional;

    public VarDeclInnerListElementDerived1 (SquareBracketsOptional SquareBracketsOptional) {
        this.SquareBracketsOptional=SquareBracketsOptional;
        if(SquareBracketsOptional!=null) SquareBracketsOptional.setParent(this);
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
        if(SquareBracketsOptional!=null) SquareBracketsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclInnerListElementDerived1(\n");

        if(SquareBracketsOptional!=null)
            buffer.append(SquareBracketsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclInnerListElementDerived1]");
        return buffer.toString();
    }
}
