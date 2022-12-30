// generated with ast extension for cup
// version 0.8
// 30/11/2022 23:8:13


package rs.ac.bg.etf.pp1.ast;

public class FactorDesignator extends Factor {

    private Designator Designator;
    private ParenActParsOptional ParenActParsOptional;

    public FactorDesignator (Designator Designator, ParenActParsOptional ParenActParsOptional) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ParenActParsOptional=ParenActParsOptional;
        if(ParenActParsOptional!=null) ParenActParsOptional.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ParenActParsOptional getParenActParsOptional() {
        return ParenActParsOptional;
    }

    public void setParenActParsOptional(ParenActParsOptional ParenActParsOptional) {
        this.ParenActParsOptional=ParenActParsOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ParenActParsOptional!=null) ParenActParsOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ParenActParsOptional!=null) ParenActParsOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ParenActParsOptional!=null) ParenActParsOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorDesignator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ParenActParsOptional!=null)
            buffer.append(ParenActParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorDesignator]");
        return buffer.toString();
    }
}
