// generated with ast extension for cup
// version 0.8
// 5/0/2023 20:23:17


package rs.ac.bg.etf.pp1.ast;

public class ForeachStatement implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Designator Designator;
    private StatementForeachStart StatementForeachStart;
    private ForeachVar ForeachVar;
    private Statement Statement;

    public ForeachStatement (Designator Designator, StatementForeachStart StatementForeachStart, ForeachVar ForeachVar, Statement Statement) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.StatementForeachStart=StatementForeachStart;
        if(StatementForeachStart!=null) StatementForeachStart.setParent(this);
        this.ForeachVar=ForeachVar;
        if(ForeachVar!=null) ForeachVar.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public StatementForeachStart getStatementForeachStart() {
        return StatementForeachStart;
    }

    public void setStatementForeachStart(StatementForeachStart StatementForeachStart) {
        this.StatementForeachStart=StatementForeachStart;
    }

    public ForeachVar getForeachVar() {
        return ForeachVar;
    }

    public void setForeachVar(ForeachVar ForeachVar) {
        this.ForeachVar=ForeachVar;
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
        if(Designator!=null) Designator.accept(visitor);
        if(StatementForeachStart!=null) StatementForeachStart.accept(visitor);
        if(ForeachVar!=null) ForeachVar.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(StatementForeachStart!=null) StatementForeachStart.traverseTopDown(visitor);
        if(ForeachVar!=null) ForeachVar.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(StatementForeachStart!=null) StatementForeachStart.traverseBottomUp(visitor);
        if(ForeachVar!=null) ForeachVar.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachStatement(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementForeachStart!=null)
            buffer.append(StatementForeachStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachVar!=null)
            buffer.append(ForeachVar.toString("  "+tab));
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
