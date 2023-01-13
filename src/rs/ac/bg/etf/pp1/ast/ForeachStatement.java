// generated with ast extension for cup
// version 0.8
// 13/0/2023 16:9:0


package rs.ac.bg.etf.pp1.ast;

public class ForeachStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ForeachStatementBeginning ForeachStatementBeginning;
    private Statement Statement;

    public ForeachStatement (ForeachStatementBeginning ForeachStatementBeginning, Statement Statement) {
        this.ForeachStatementBeginning=ForeachStatementBeginning;
        if(ForeachStatementBeginning!=null) ForeachStatementBeginning.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForeachStatementBeginning getForeachStatementBeginning() {
        return ForeachStatementBeginning;
    }

    public void setForeachStatementBeginning(ForeachStatementBeginning ForeachStatementBeginning) {
        this.ForeachStatementBeginning=ForeachStatementBeginning;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
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
        if(ForeachStatementBeginning!=null) ForeachStatementBeginning.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachStatementBeginning!=null) ForeachStatementBeginning.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachStatementBeginning!=null) ForeachStatementBeginning.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachStatement(\n");

        if(ForeachStatementBeginning!=null)
            buffer.append(ForeachStatementBeginning.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachStatement]");
        return buffer.toString();
    }
}
