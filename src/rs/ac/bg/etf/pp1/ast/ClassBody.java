// generated with ast extension for cup
// version 0.8
// 7/0/2023 17:25:11


package rs.ac.bg.etf.pp1.ast;

public class ClassBody implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private VarDeclListE VarDeclListE;
    private ClassConstructorsAndMethodsDeclOptional ClassConstructorsAndMethodsDeclOptional;

    public ClassBody (VarDeclListE VarDeclListE, ClassConstructorsAndMethodsDeclOptional ClassConstructorsAndMethodsDeclOptional) {
        this.VarDeclListE=VarDeclListE;
        if(VarDeclListE!=null) VarDeclListE.setParent(this);
        this.ClassConstructorsAndMethodsDeclOptional=ClassConstructorsAndMethodsDeclOptional;
        if(ClassConstructorsAndMethodsDeclOptional!=null) ClassConstructorsAndMethodsDeclOptional.setParent(this);
    }

    public VarDeclListE getVarDeclListE() {
        return VarDeclListE;
    }

    public void setVarDeclListE(VarDeclListE VarDeclListE) {
        this.VarDeclListE=VarDeclListE;
    }

    public ClassConstructorsAndMethodsDeclOptional getClassConstructorsAndMethodsDeclOptional() {
        return ClassConstructorsAndMethodsDeclOptional;
    }

    public void setClassConstructorsAndMethodsDeclOptional(ClassConstructorsAndMethodsDeclOptional ClassConstructorsAndMethodsDeclOptional) {
        this.ClassConstructorsAndMethodsDeclOptional=ClassConstructorsAndMethodsDeclOptional;
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
        if(VarDeclListE!=null) VarDeclListE.accept(visitor);
        if(ClassConstructorsAndMethodsDeclOptional!=null) ClassConstructorsAndMethodsDeclOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListE!=null) VarDeclListE.traverseTopDown(visitor);
        if(ClassConstructorsAndMethodsDeclOptional!=null) ClassConstructorsAndMethodsDeclOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListE!=null) VarDeclListE.traverseBottomUp(visitor);
        if(ClassConstructorsAndMethodsDeclOptional!=null) ClassConstructorsAndMethodsDeclOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassBody(\n");

        if(VarDeclListE!=null)
            buffer.append(VarDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassConstructorsAndMethodsDeclOptional!=null)
            buffer.append(ClassConstructorsAndMethodsDeclOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassBody]");
        return buffer.toString();
    }
}
