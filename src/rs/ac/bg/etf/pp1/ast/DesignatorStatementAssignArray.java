// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:34:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementAssignArray extends DesignatorStatement {

    private DesignatorStatementAssignArrayStart DesignatorStatementAssignArrayStart;
    private DesignatorListOptionalES DesignatorListOptionalES;
    private Designator Designator;

    public DesignatorStatementAssignArray (DesignatorStatementAssignArrayStart DesignatorStatementAssignArrayStart, DesignatorListOptionalES DesignatorListOptionalES, Designator Designator) {
        this.DesignatorStatementAssignArrayStart=DesignatorStatementAssignArrayStart;
        if(DesignatorStatementAssignArrayStart!=null) DesignatorStatementAssignArrayStart.setParent(this);
        this.DesignatorListOptionalES=DesignatorListOptionalES;
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorStatementAssignArrayStart getDesignatorStatementAssignArrayStart() {
        return DesignatorStatementAssignArrayStart;
    }

    public void setDesignatorStatementAssignArrayStart(DesignatorStatementAssignArrayStart DesignatorStatementAssignArrayStart) {
        this.DesignatorStatementAssignArrayStart=DesignatorStatementAssignArrayStart;
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
        if(DesignatorStatementAssignArrayStart!=null) DesignatorStatementAssignArrayStart.accept(visitor);
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementAssignArrayStart!=null) DesignatorStatementAssignArrayStart.traverseTopDown(visitor);
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementAssignArrayStart!=null) DesignatorStatementAssignArrayStart.traverseBottomUp(visitor);
        if(DesignatorListOptionalES!=null) DesignatorListOptionalES.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementAssignArray(\n");

        if(DesignatorStatementAssignArrayStart!=null)
            buffer.append(DesignatorStatementAssignArrayStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [DesignatorStatementAssignArray]");
        return buffer.toString();
    }
}
