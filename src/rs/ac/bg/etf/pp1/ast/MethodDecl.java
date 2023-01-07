// generated with ast extension for cup
// version 0.8
// 7/0/2023 17:25:11


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodReturnType MethodReturnType;
    private MethodName MethodName;
    private FormParsOptional FormParsOptional;
    private VarDeclListE VarDeclListE;
    private StatementListE StatementListE;

    public MethodDecl (MethodReturnType MethodReturnType, MethodName MethodName, FormParsOptional FormParsOptional, VarDeclListE VarDeclListE, StatementListE StatementListE) {
        this.MethodReturnType=MethodReturnType;
        if(MethodReturnType!=null) MethodReturnType.setParent(this);
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
        this.FormParsOptional=FormParsOptional;
        if(FormParsOptional!=null) FormParsOptional.setParent(this);
        this.VarDeclListE=VarDeclListE;
        if(VarDeclListE!=null) VarDeclListE.setParent(this);
        this.StatementListE=StatementListE;
        if(StatementListE!=null) StatementListE.setParent(this);
    }

    public MethodReturnType getMethodReturnType() {
        return MethodReturnType;
    }

    public void setMethodReturnType(MethodReturnType MethodReturnType) {
        this.MethodReturnType=MethodReturnType;
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public FormParsOptional getFormParsOptional() {
        return FormParsOptional;
    }

    public void setFormParsOptional(FormParsOptional FormParsOptional) {
        this.FormParsOptional=FormParsOptional;
    }

    public VarDeclListE getVarDeclListE() {
        return VarDeclListE;
    }

    public void setVarDeclListE(VarDeclListE VarDeclListE) {
        this.VarDeclListE=VarDeclListE;
    }

    public StatementListE getStatementListE() {
        return StatementListE;
    }

    public void setStatementListE(StatementListE StatementListE) {
        this.StatementListE=StatementListE;
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
        if(MethodReturnType!=null) MethodReturnType.accept(visitor);
        if(MethodName!=null) MethodName.accept(visitor);
        if(FormParsOptional!=null) FormParsOptional.accept(visitor);
        if(VarDeclListE!=null) VarDeclListE.accept(visitor);
        if(StatementListE!=null) StatementListE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodReturnType!=null) MethodReturnType.traverseTopDown(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
        if(FormParsOptional!=null) FormParsOptional.traverseTopDown(visitor);
        if(VarDeclListE!=null) VarDeclListE.traverseTopDown(visitor);
        if(StatementListE!=null) StatementListE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodReturnType!=null) MethodReturnType.traverseBottomUp(visitor);
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        if(FormParsOptional!=null) FormParsOptional.traverseBottomUp(visitor);
        if(VarDeclListE!=null) VarDeclListE.traverseBottomUp(visitor);
        if(StatementListE!=null) StatementListE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodReturnType!=null)
            buffer.append(MethodReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsOptional!=null)
            buffer.append(FormParsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListE!=null)
            buffer.append(VarDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementListE!=null)
            buffer.append(StatementListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
