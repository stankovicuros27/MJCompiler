// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:8:42


package rs.ac.bg.etf.pp1.ast;

public class ConditionMultiple extends Condition {

    private Condition Condition;
    private PlaceAfterFirstConditionInOr PlaceAfterFirstConditionInOr;
    private CondTerm CondTerm;

    public ConditionMultiple (Condition Condition, PlaceAfterFirstConditionInOr PlaceAfterFirstConditionInOr, CondTerm CondTerm) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.PlaceAfterFirstConditionInOr=PlaceAfterFirstConditionInOr;
        if(PlaceAfterFirstConditionInOr!=null) PlaceAfterFirstConditionInOr.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public PlaceAfterFirstConditionInOr getPlaceAfterFirstConditionInOr() {
        return PlaceAfterFirstConditionInOr;
    }

    public void setPlaceAfterFirstConditionInOr(PlaceAfterFirstConditionInOr PlaceAfterFirstConditionInOr) {
        this.PlaceAfterFirstConditionInOr=PlaceAfterFirstConditionInOr;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(PlaceAfterFirstConditionInOr!=null) PlaceAfterFirstConditionInOr.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(PlaceAfterFirstConditionInOr!=null) PlaceAfterFirstConditionInOr.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(PlaceAfterFirstConditionInOr!=null) PlaceAfterFirstConditionInOr.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionMultiple(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlaceAfterFirstConditionInOr!=null)
            buffer.append(PlaceAfterFirstConditionInOr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionMultiple]");
        return buffer.toString();
    }
}
