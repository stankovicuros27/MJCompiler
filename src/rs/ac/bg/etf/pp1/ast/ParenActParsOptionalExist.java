// generated with ast extension for cup
// version 0.8
// 30/11/2022 23:8:13


package rs.ac.bg.etf.pp1.ast;

public class ParenActParsOptionalExist extends ParenActParsOptional {

    private ActParsOptional ActParsOptional;

    public ParenActParsOptionalExist (ActParsOptional ActParsOptional) {
        this.ActParsOptional=ActParsOptional;
        if(ActParsOptional!=null) ActParsOptional.setParent(this);
    }

    public ActParsOptional getActParsOptional() {
        return ActParsOptional;
    }

    public void setActParsOptional(ActParsOptional ActParsOptional) {
        this.ActParsOptional=ActParsOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsOptional!=null) ActParsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsOptional!=null) ActParsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsOptional!=null) ActParsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ParenActParsOptionalExist(\n");

        if(ActParsOptional!=null)
            buffer.append(ActParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ParenActParsOptionalExist]");
        return buffer.toString();
    }
}
