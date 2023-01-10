// generated with ast extension for cup
// version 0.8
// 10/0/2023 15:46:15


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ProgramName ProgramName;
    private ProgramDeclListE ProgramDeclListE;
    private MethodDeclListE MethodDeclListE;

    public Program (ProgramName ProgramName, ProgramDeclListE ProgramDeclListE, MethodDeclListE MethodDeclListE) {
        this.ProgramName=ProgramName;
        if(ProgramName!=null) ProgramName.setParent(this);
        this.ProgramDeclListE=ProgramDeclListE;
        if(ProgramDeclListE!=null) ProgramDeclListE.setParent(this);
        this.MethodDeclListE=MethodDeclListE;
        if(MethodDeclListE!=null) MethodDeclListE.setParent(this);
    }

    public ProgramName getProgramName() {
        return ProgramName;
    }

    public void setProgramName(ProgramName ProgramName) {
        this.ProgramName=ProgramName;
    }

    public ProgramDeclListE getProgramDeclListE() {
        return ProgramDeclListE;
    }

    public void setProgramDeclListE(ProgramDeclListE ProgramDeclListE) {
        this.ProgramDeclListE=ProgramDeclListE;
    }

    public MethodDeclListE getMethodDeclListE() {
        return MethodDeclListE;
    }

    public void setMethodDeclListE(MethodDeclListE MethodDeclListE) {
        this.MethodDeclListE=MethodDeclListE;
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
        if(ProgramName!=null) ProgramName.accept(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.accept(visitor);
        if(MethodDeclListE!=null) MethodDeclListE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramName!=null) ProgramName.traverseTopDown(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseTopDown(visitor);
        if(MethodDeclListE!=null) MethodDeclListE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramName!=null) ProgramName.traverseBottomUp(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseBottomUp(visitor);
        if(MethodDeclListE!=null) MethodDeclListE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(ProgramName!=null)
            buffer.append(ProgramName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ProgramDeclListE!=null)
            buffer.append(ProgramDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclListE!=null)
            buffer.append(MethodDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
