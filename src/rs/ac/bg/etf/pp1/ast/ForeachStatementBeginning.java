// generated with ast extension for cup
// version 0.8
// 13/0/2023 16:9:0


package rs.ac.bg.etf.pp1.ast;

public class ForeachStatementBeginning implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Designator Designator;
    private ForeachVarDesignator ForeachVarDesignator;

    public ForeachStatementBeginning (Designator Designator, ForeachVarDesignator ForeachVarDesignator) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ForeachVarDesignator=ForeachVarDesignator;
        if(ForeachVarDesignator!=null) ForeachVarDesignator.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ForeachVarDesignator getForeachVarDesignator() {
        return ForeachVarDesignator;
    }

    public void setForeachVarDesignator(ForeachVarDesignator ForeachVarDesignator) {
        this.ForeachVarDesignator=ForeachVarDesignator;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ForeachVarDesignator!=null) ForeachVarDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ForeachVarDesignator!=null) ForeachVarDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ForeachVarDesignator!=null) ForeachVarDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachStatementBeginning(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachVarDesignator!=null)
            buffer.append(ForeachVarDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachStatementBeginning]");
        return buffer.toString();
    }
}
