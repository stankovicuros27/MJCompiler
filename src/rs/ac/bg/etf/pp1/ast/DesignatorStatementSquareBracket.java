// generated with ast extension for cup
// version 0.8
// 30/11/2022 18:23:9


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementSquareBracket extends DesignatorStatement {

    private DesignatorListOptionalES DesignatorListOptionalES;
    private Designator Designator;

    public DesignatorStatementSquareBracket (DesignatorListOptionalES DesignatorListOptionalES, Designator Designator) {
        this.DesignatorListOptionalES=DesignatorListOptionalES;
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorListOptionalES getDesignatorListOptionalES() {
        return DesignatorListOptionalES;
    }

    public void setDesignatorListOptionalES(DesignatorListOptionalES DesignatorListOptionalES) {
        this.DesignatorListOptionalES=DesignatorListOptionalES;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementSquareBracket(\n");

        if(DesignatorListOptionalES!=null)
            buffer.append(DesignatorListOptionalES.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementSquareBracket]");
        return buffer.toString();
    }
}
