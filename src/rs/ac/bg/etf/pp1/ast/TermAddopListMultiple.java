// generated with ast extension for cup
// version 0.8
// 30/11/2022 18:23:9


package rs.ac.bg.etf.pp1.ast;

public class TermAddopListMultiple extends TermAddopList {

    private TermAddopList TermAddopList;
    private Addop Addop;
    private Term Term;

    public TermAddopListMultiple (TermAddopList TermAddopList, Addop Addop, Term Term) {
        this.TermAddopList=TermAddopList;
        if(TermAddopList!=null) TermAddopList.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public TermAddopList getTermAddopList() {
        return TermAddopList;
    }

    public void setTermAddopList(TermAddopList TermAddopList) {
        this.TermAddopList=TermAddopList;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermAddopList!=null) TermAddopList.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermAddopList!=null) TermAddopList.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermAddopList!=null) TermAddopList.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermAddopListMultiple(\n");

        if(TermAddopList!=null)
            buffer.append(TermAddopList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermAddopListMultiple]");
        return buffer.toString();
    }
}