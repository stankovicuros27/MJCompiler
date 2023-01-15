// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:3:3


package rs.ac.bg.etf.pp1.ast;

public class StatementArrMax extends Statement {

    private ArrMaxArrDesignator ArrMaxArrDesignator;

    public StatementArrMax (ArrMaxArrDesignator ArrMaxArrDesignator) {
        this.ArrMaxArrDesignator=ArrMaxArrDesignator;
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.setParent(this);
    }

    public ArrMaxArrDesignator getArrMaxArrDesignator() {
        return ArrMaxArrDesignator;
    }

    public void setArrMaxArrDesignator(ArrMaxArrDesignator ArrMaxArrDesignator) {
        this.ArrMaxArrDesignator=ArrMaxArrDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrMaxArrDesignator!=null) ArrMaxArrDesignator.traverseBottomUp(visitor);
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

        buffer.append(tab);
        buffer.append(") [StatementArrMax]");
        return buffer.toString();
    }
}
