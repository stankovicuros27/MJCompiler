// generated with ast extension for cup
// version 0.8
// 3/0/2023 19:20:51


package rs.ac.bg.etf.pp1.ast;

public class ClassConstructorsAndMethodsDeclOptionalExists extends ClassConstructorsAndMethodsDeclOptional {

    private MethodDeclListE MethodDeclListE;

    public ClassConstructorsAndMethodsDeclOptionalExists (MethodDeclListE MethodDeclListE) {
        this.MethodDeclListE=MethodDeclListE;
        if(MethodDeclListE!=null) MethodDeclListE.setParent(this);
    }

    public MethodDeclListE getMethodDeclListE() {
        return MethodDeclListE;
    }

    public void setMethodDeclListE(MethodDeclListE MethodDeclListE) {
        this.MethodDeclListE=MethodDeclListE;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclListE!=null) MethodDeclListE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListE!=null) MethodDeclListE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListE!=null) MethodDeclListE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassConstructorsAndMethodsDeclOptionalExists(\n");

        if(MethodDeclListE!=null)
            buffer.append(MethodDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassConstructorsAndMethodsDeclOptionalExists]");
        return buffer.toString();
    }
}
