// generated with ast extension for cup
// version 0.8
// 11/0/2023 17:16:27


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementAction extends DesignatorStatement {

    private DesignatorAction DesignatorAction;

    public DesignatorStatementAction (DesignatorAction DesignatorAction) {
        this.DesignatorAction=DesignatorAction;
        if(DesignatorAction!=null) DesignatorAction.setParent(this);
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
        if(DesignatorAction!=null) DesignatorAction.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAction!=null) DesignatorAction.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAction!=null) DesignatorAction.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementAction(\n");

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
