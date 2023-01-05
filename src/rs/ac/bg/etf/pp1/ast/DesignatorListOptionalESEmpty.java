// generated with ast extension for cup
// version 0.8
// 5/0/2023 18:3:8


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListOptionalESEmpty extends DesignatorListOptionalES {

    private DesignatorListOptionalES DesignatorListOptionalES;
    private DesignatorOptional DesignatorOptional;

    public DesignatorListOptionalESEmpty (DesignatorListOptionalES DesignatorListOptionalES, DesignatorOptional DesignatorOptional) {
        this.DesignatorListOptionalES=DesignatorListOptionalES;
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.setParent(this);
        this.DesignatorOptional=DesignatorOptional;
        if(DesignatorOptional!=null) DesignatorOptional.setParent(this);
    }

    public DesignatorListOptionalES getDesignatorListOptionalES() {
        return DesignatorListOptionalES;
    }

    public void setDesignatorListOptionalES(DesignatorListOptionalES DesignatorListOptionalES) {
        this.DesignatorListOptionalES=DesignatorListOptionalES;
    }

    public DesignatorOptional getDesignatorOptional() {
        return DesignatorOptional;
    }

    public void setDesignatorOptional(DesignatorOptional DesignatorOptional) {
        this.DesignatorOptional=DesignatorOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.accept(visitor);
        if(DesignatorOptional!=null) DesignatorOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseTopDown(visitor);
        if(DesignatorOptional!=null) DesignatorOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseBottomUp(visitor);
        if(DesignatorOptional!=null) DesignatorOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListOptionalESEmpty(\n");

        if(DesignatorListOptionalES!=null)
            buffer.append(DesignatorListOptionalES.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorOptional!=null)
            buffer.append(DesignatorOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListOptionalESEmpty]");
        return buffer.toString();
    }
}
