// generated with ast extension for cup
// version 0.8
// 10/0/2023 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class IfStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private IfStatementStart IfStatementStart;
    private ConditionInIf ConditionInIf;
    private PlaceAfterIfCondition PlaceAfterIfCondition;
    private Statement Statement;
    private PlaceAfterIfBlock PlaceAfterIfBlock;

    public IfStatement (IfStatementStart IfStatementStart, ConditionInIf ConditionInIf, PlaceAfterIfCondition PlaceAfterIfCondition, Statement Statement, PlaceAfterIfBlock PlaceAfterIfBlock) {
        this.IfStatementStart=IfStatementStart;
        if(IfStatementStart!=null) IfStatementStart.setParent(this);
        this.ConditionInIf=ConditionInIf;
        if(ConditionInIf!=null) ConditionInIf.setParent(this);
        this.PlaceAfterIfCondition=PlaceAfterIfCondition;
        if(PlaceAfterIfCondition!=null) PlaceAfterIfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.PlaceAfterIfBlock=PlaceAfterIfBlock;
        if(PlaceAfterIfBlock!=null) PlaceAfterIfBlock.setParent(this);
    }

    public IfStatementStart getIfStatementStart() {
        return IfStatementStart;
    }

    public void setIfStatementStart(IfStatementStart IfStatementStart) {
        this.IfStatementStart=IfStatementStart;
    }

    public ConditionInIf getConditionInIf() {
        return ConditionInIf;
    }

    public void setConditionInIf(ConditionInIf ConditionInIf) {
        this.ConditionInIf=ConditionInIf;
    }

    public PlaceAfterIfCondition getPlaceAfterIfCondition() {
        return PlaceAfterIfCondition;
    }

    public void setPlaceAfterIfCondition(PlaceAfterIfCondition PlaceAfterIfCondition) {
        this.PlaceAfterIfCondition=PlaceAfterIfCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public PlaceAfterIfBlock getPlaceAfterIfBlock() {
        return PlaceAfterIfBlock;
    }

    public void setPlaceAfterIfBlock(PlaceAfterIfBlock PlaceAfterIfBlock) {
        this.PlaceAfterIfBlock=PlaceAfterIfBlock;
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
        if(IfStatementStart!=null) IfStatementStart.accept(visitor);
        if(ConditionInIf!=null) ConditionInIf.accept(visitor);
        if(PlaceAfterIfCondition!=null) PlaceAfterIfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(PlaceAfterIfBlock!=null) PlaceAfterIfBlock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStatementStart!=null) IfStatementStart.traverseTopDown(visitor);
        if(ConditionInIf!=null) ConditionInIf.traverseTopDown(visitor);
        if(PlaceAfterIfCondition!=null) PlaceAfterIfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(PlaceAfterIfBlock!=null) PlaceAfterIfBlock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStatementStart!=null) IfStatementStart.traverseBottomUp(visitor);
        if(ConditionInIf!=null) ConditionInIf.traverseBottomUp(visitor);
        if(PlaceAfterIfCondition!=null) PlaceAfterIfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(PlaceAfterIfBlock!=null) PlaceAfterIfBlock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(IfStatementStart!=null)
            buffer.append(IfStatementStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionInIf!=null)
            buffer.append(ConditionInIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlaceAfterIfCondition!=null)
            buffer.append(PlaceAfterIfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlaceAfterIfBlock!=null)
            buffer.append(PlaceAfterIfBlock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
