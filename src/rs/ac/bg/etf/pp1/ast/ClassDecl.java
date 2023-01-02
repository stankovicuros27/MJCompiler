// generated with ast extension for cup
// version 0.8
// 2/0/2023 17:7:52


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ExtendsTypeOptional ExtendsTypeOptional;
    private ClassBody ClassBody;

    public ClassDecl (ExtendsTypeOptional ExtendsTypeOptional, ClassBody ClassBody) {
        this.ExtendsTypeOptional=ExtendsTypeOptional;
        if(ExtendsTypeOptional!=null) ExtendsTypeOptional.setParent(this);
        this.ClassBody=ClassBody;
        if(ClassBody!=null) ClassBody.setParent(this);
    }

    public ExtendsTypeOptional getExtendsTypeOptional() {
        return ExtendsTypeOptional;
    }

    public void setExtendsTypeOptional(ExtendsTypeOptional ExtendsTypeOptional) {
        this.ExtendsTypeOptional=ExtendsTypeOptional;
    }

    public ClassBody getClassBody() {
        return ClassBody;
    }

    public void setClassBody(ClassBody ClassBody) {
        this.ClassBody=ClassBody;
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
        if(ExtendsTypeOptional!=null) ExtendsTypeOptional.accept(visitor);
        if(ClassBody!=null) ClassBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendsTypeOptional!=null) ExtendsTypeOptional.traverseTopDown(visitor);
        if(ClassBody!=null) ClassBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendsTypeOptional!=null) ExtendsTypeOptional.traverseBottomUp(visitor);
        if(ClassBody!=null) ClassBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ExtendsTypeOptional!=null)
            buffer.append(ExtendsTypeOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassBody!=null)
            buffer.append(ClassBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
