// generated with ast extension for cup
// version 0.8
// 5/0/2023 1:37:25


package rs.ac.bg.etf.pp1.ast;

public class IfStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConditionInIf ConditionInIf;
    private Statement Statement;

    public IfStatement (ConditionInIf ConditionInIf, Statement Statement) {
        this.ConditionInIf=ConditionInIf;
        if(ConditionInIf!=null) ConditionInIf.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ConditionInIf getConditionInIf() {
        return ConditionInIf;
    }

    public void setConditionInIf(ConditionInIf ConditionInIf) {
        this.ConditionInIf=ConditionInIf;
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
        if(ConditionInIf!=null) ConditionInIf.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionInIf!=null) ConditionInIf.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionInIf!=null) ConditionInIf.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(ConditionInIf!=null)
            buffer.append(ConditionInIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}