// generated with ast extension for cup
// version 0.8
// 13/0/2023 16:9:0


package rs.ac.bg.etf.pp1.ast;

public class StatementArrSort extends Statement {

    private ArrSortArrDesignator ArrSortArrDesignator;

    public StatementArrSort (ArrSortArrDesignator ArrSortArrDesignator) {
        this.ArrSortArrDesignator=ArrSortArrDesignator;
        if(ArrSortArrDesignator!=null) ArrSortArrDesignator.setParent(this);
    }

    public ArrSortArrDesignator getArrSortArrDesignator() {
        return ArrSortArrDesignator;
    }

    public void setArrSortArrDesignator(ArrSortArrDesignator ArrSortArrDesignator) {
        this.ArrSortArrDesignator=ArrSortArrDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrSortArrDesignator!=null) ArrSortArrDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrSortArrDesignator!=null) ArrSortArrDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrSortArrDesignator!=null) ArrSortArrDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementArrSort(\n");

        if(ArrSortArrDesignator!=null)
            buffer.append(ArrSortArrDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementArrSort]");
        return buffer.toString();
    }
}
