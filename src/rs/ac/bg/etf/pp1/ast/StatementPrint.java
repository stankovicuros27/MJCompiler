// generated with ast extension for cup
// version 0.8
// 5/0/2023 20:23:17


package rs.ac.bg.etf.pp1.ast;

public class StatementPrint extends Statement {

    private Expr Expr;
    private PrintNumConstOptional PrintNumConstOptional;

    public StatementPrint (Expr Expr, PrintNumConstOptional PrintNumConstOptional) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintNumConstOptional=PrintNumConstOptional;
        if(PrintNumConstOptional!=null) PrintNumConstOptional.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintNumConstOptional getPrintNumConstOptional() {
        return PrintNumConstOptional;
    }

    public void setPrintNumConstOptional(PrintNumConstOptional PrintNumConstOptional) {
        this.PrintNumConstOptional=PrintNumConstOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintNumConstOptional!=null) PrintNumConstOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintNumConstOptional!=null) PrintNumConstOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintNumConstOptional!=null) PrintNumConstOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintNumConstOptional!=null)
            buffer.append(PrintNumConstOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrint]");
        return buffer.toString();
    }
}
