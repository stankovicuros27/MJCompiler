// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:3:3


package rs.ac.bg.etf.pp1.ast;

public class ElseStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ElseStatementStart ElseStatementStart;
    private Statement Statement;
    private PlaceAfterElseBlock PlaceAfterElseBlock;

    public ElseStatement (ElseStatementStart ElseStatementStart, Statement Statement, PlaceAfterElseBlock PlaceAfterElseBlock) {
        this.ElseStatementStart=ElseStatementStart;
        if(ElseStatementStart!=null) ElseStatementStart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.PlaceAfterElseBlock=PlaceAfterElseBlock;
        if(PlaceAfterElseBlock!=null) PlaceAfterElseBlock.setParent(this);
    }

    public ElseStatementStart getElseStatementStart() {
        return ElseStatementStart;
    }

    public void setElseStatementStart(ElseStatementStart ElseStatementStart) {
        this.ElseStatementStart=ElseStatementStart;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public PlaceAfterElseBlock getPlaceAfterElseBlock() {
        return PlaceAfterElseBlock;
    }

    public void setPlaceAfterElseBlock(PlaceAfterElseBlock PlaceAfterElseBlock) {
        this.PlaceAfterElseBlock=PlaceAfterElseBlock;
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
        if(ElseStatementStart!=null) ElseStatementStart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(PlaceAfterElseBlock!=null) PlaceAfterElseBlock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ElseStatementStart!=null) ElseStatementStart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(PlaceAfterElseBlock!=null) PlaceAfterElseBlock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ElseStatementStart!=null) ElseStatementStart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(PlaceAfterElseBlock!=null) PlaceAfterElseBlock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStatement(\n");

        if(ElseStatementStart!=null)
            buffer.append(ElseStatementStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PlaceAfterElseBlock!=null)
            buffer.append(PlaceAfterElseBlock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStatement]");
        return buffer.toString();
    }
}
