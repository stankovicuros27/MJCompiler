// generated with ast extension for cup
// version 0.8
// 30/11/2022 18:23:9


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementAction extends DesignatorStatement {

    private Designator Designator;
    private DesignatorAction DesignatorAction;

    public DesignatorStatementAction (Designator Designator, DesignatorAction DesignatorAction) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorAction=DesignatorAction;
        if(DesignatorAction!=null) DesignatorAction.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorAction getDesignatorAction() {
        return DesignatorAction;
    }

    public void setDesignatorAction(DesignatorAction DesignatorAction) {
        this.DesignatorAction=DesignatorAction;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorAction!=null) DesignatorAction.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorAction!=null) DesignatorAction.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorAction!=null) DesignatorAction.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementAction(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorAction!=null)
            buffer.append(DesignatorAction.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementAction]");
        return buffer.toString();
    }
}
