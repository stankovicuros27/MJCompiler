// generated with ast extension for cup
// version 0.8
// 2/0/2023 17:7:52


package rs.ac.bg.etf.pp1.ast;

public class FormParamDerived1 extends FormParam {

    private Type Type;
    private SquareBracketsOptional SquareBracketsOptional;

    public FormParamDerived1 (Type Type, SquareBracketsOptional SquareBracketsOptional) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.SquareBracketsOptional=SquareBracketsOptional;
        if(SquareBracketsOptional!=null) SquareBracketsOptional.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(SquareBracketsOptional!=null) SquareBracketsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParamDerived1(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SquareBracketsOptional!=null)
            buffer.append(SquareBracketsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamDerived1]");
        return buffer.toString();
    }
}
