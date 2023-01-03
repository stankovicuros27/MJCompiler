// generated with ast extension for cup
// version 0.8
// 3/0/2023 2:22:1


package rs.ac.bg.etf.pp1.ast;

public class MatchedStatementSection extends MatchedStatement {

    private StatementListE StatementListE;

    public MatchedStatementSection (StatementListE StatementListE) {
        this.StatementListE=StatementListE;
        if(StatementListE!=null) StatementListE.setParent(this);
    }

    public StatementListE getStatementListE() {
        return StatementListE;
    }

    public void setStatementListE(StatementListE StatementListE) {
        this.StatementListE=StatementListE;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementListE!=null) StatementListE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementListE!=null) StatementListE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementListE!=null) StatementListE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedStatementSection(\n");

        if(StatementListE!=null)
            buffer.append(StatementListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedStatementSection]");
        return buffer.toString();
    }
}
