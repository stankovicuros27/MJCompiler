// generated with ast extension for cup
// version 0.8
// 3/0/2023 17:26:13


package rs.ac.bg.etf.pp1.ast;

public class FactorNew extends Factor {

    private Type Type;
    private NewTypeSuffix NewTypeSuffix;

    public FactorNew (Type Type, NewTypeSuffix NewTypeSuffix) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NewTypeSuffix=NewTypeSuffix;
        if(NewTypeSuffix!=null) NewTypeSuffix.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public NewTypeSuffix getNewTypeSuffix() {
        return NewTypeSuffix;
    }

    public void setNewTypeSuffix(NewTypeSuffix NewTypeSuffix) {
        this.NewTypeSuffix=NewTypeSuffix;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(NewTypeSuffix!=null) NewTypeSuffix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NewTypeSuffix!=null) NewTypeSuffix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NewTypeSuffix!=null) NewTypeSuffix.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNew(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewTypeSuffix!=null)
            buffer.append(NewTypeSuffix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNew]");
        return buffer.toString();
    }
}
