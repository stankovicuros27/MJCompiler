// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:41:32


package rs.ac.bg.etf.pp1.ast;

public class CondTermMultiple extends CondTerm {

    private CondTerm CondTerm;
    private PlaceAfterFirstConditionInAnd PlaceAfterFirstConditionInAnd;
    private CondFact CondFact;

    public CondTermMultiple (CondTerm CondTerm, PlaceAfterFirstConditionInAnd PlaceAfterFirstConditionInAnd, CondFact CondFact) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.PlaceAfterFirstConditionInAnd=PlaceAfterFirstConditionInAnd;
        if(PlaceAfterFirstConditionInAnd!=null) PlaceAfterFirstConditionInAnd.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public PlaceAfterFirstConditionInAnd getPlaceAfterFirstConditionInAnd() {
        return PlaceAfterFirstConditionInAnd;
    }

    public void setPlaceAfterFirstConditionInAnd(PlaceAfterFirstConditionInAnd PlaceAfterFirstConditionInAnd) {
        this.PlaceAfterFirstConditionInAnd=PlaceAfterFirstConditionInAnd;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(PlaceAfterFirstConditionInAnd!=null) PlaceAfterFirstConditionInAnd.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(PlaceAfterFirstConditionInAnd!=null) PlaceAfterFirstConditionInAnd.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(PlaceAfterFirstConditionInAnd!=null) PlaceAfterFirstConditionInAnd.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermMultiple(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlaceAfterFirstConditionInAnd!=null)
            buffer.append(PlaceAfterFirstConditionInAnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermMultiple]");
        return buffer.toString();
    }
}
