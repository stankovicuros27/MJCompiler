// generated with ast extension for cup
// version 0.8
// 15/0/2023 16:41:32


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclListEExists extends MethodDeclListE {

    private MethodDeclListE MethodDeclListE;
    private MethodDecl MethodDecl;

    public MethodDeclListEExists (MethodDeclListE MethodDeclListE, MethodDecl MethodDecl) {
        this.MethodDeclListE=MethodDeclListE;
        if(MethodDeclListE!=null) MethodDeclListE.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodDeclListE getMethodDeclListE() {
        return MethodDeclListE;
    }

    public void setMethodDeclListE(MethodDeclListE MethodDeclListE) {
        this.MethodDeclListE=MethodDeclListE;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclListE!=null) MethodDeclListE.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListE!=null) MethodDeclListE.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListE!=null) MethodDeclListE.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclListEExists(\n");

        if(MethodDeclListE!=null)
            buffer.append(MethodDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclListEExists]");
        return buffer.toString();
    }
}
