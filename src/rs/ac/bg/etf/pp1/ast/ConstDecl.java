// generated with ast extension for cup
// version 0.8
// 2/0/2023 17:7:52


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConstDeclInnerList ConstDeclInnerList;

    public ConstDecl (Type Type, ConstDeclInnerList ConstDeclInnerList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclInnerList=ConstDeclInnerList;
        if(ConstDeclInnerList!=null) ConstDeclInnerList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclInnerList getConstDeclInnerList() {
        return ConstDeclInnerList;
    }

    public void setConstDeclInnerList(ConstDeclInnerList ConstDeclInnerList) {
        this.ConstDeclInnerList=ConstDeclInnerList;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclInnerList!=null) ConstDeclInnerList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclInnerList!=null) ConstDeclInnerList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclInnerList!=null) ConstDeclInnerList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclInnerList!=null)
            buffer.append(ConstDeclInnerList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
