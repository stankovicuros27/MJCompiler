// generated with ast extension for cup
// version 0.8
// 11/0/2023 17:16:27


package rs.ac.bg.etf.pp1.ast;

public class StatementArrMax extends Statement {

    private ArrMaxArrDesignator ArrMaxArrDesignator;
    private ArrMaxVarDesignator ArrMaxVarDesignator;

    public StatementArrMax (ArrMaxArrDesignator ArrMaxArrDesignator, ArrMaxVarDesignator ArrMaxVarDesignator) {
        this.ArrMaxArrDesignator=ArrMaxArrDesignator;
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.setParent(this);
        this.ArrMaxVarDesignator=ArrMaxVarDesignator;
        if(ArrMaxVarDesignator!=null) ArrMaxVarDesignator.setParent(this);
    }

    public ArrMaxArrDesignator getArrMaxArrDesignator() {
        return ArrMaxArrDesignator;
    }

    public void setArrMaxArrDesignator(ArrMaxArrDesignator ArrMaxArrDesignator) {
        this.ArrMaxArrDesignator=ArrMaxArrDesignator;
    }

    public ArrMaxVarDesignator getArrMaxVarDesignator() {
        return ArrMaxVarDesignator;
    }

    public void setArrMaxVarDesignator(ArrMaxVarDesignator ArrMaxVarDesignator) {
        this.ArrMaxVarDesignator=ArrMaxVarDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.accept(visitor);
        if(ArrMaxVarDesignator!=null) ArrMaxVarDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.traverseTopDown(visitor);
        if(ArrMaxVarDesignator!=null) ArrMaxVarDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.traverseBottomUp(visitor);
        if(ArrMaxVarDesignator!=null) ArrMaxVarDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementArrMax(\n");

        if(ArrMaxArrDesignator!=null)
            buffer.append(ArrMaxArrDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrMaxVarDesignator!=null)
            buffer.append(ArrMaxVarDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementArrMax]");
        return buffer.toString();
    }
}
