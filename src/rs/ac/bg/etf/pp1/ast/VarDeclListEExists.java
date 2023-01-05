// generated with ast extension for cup
// version 0.8
// 5/0/2023 1:37:25


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListEExists extends VarDeclListE {

    private VarDeclListE VarDeclListE;
    private VarDecl VarDecl;

    public VarDeclListEExists (VarDeclListE VarDeclListE, VarDecl VarDecl) {
        this.VarDeclListE=VarDeclListE;
        if(VarDeclListE!=null) VarDeclListE.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public VarDeclListE getVarDeclListE() {
        return VarDeclListE;
    }

    public void setVarDeclListE(VarDeclListE VarDeclListE) {
        this.VarDeclListE=VarDeclListE;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListE!=null) VarDeclListE.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListE!=null) VarDeclListE.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListE!=null) VarDeclListE.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclListEExists(\n");

        if(VarDeclListE!=null)
            buffer.append(VarDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListEExists]");
        return buffer.toString();
    }
}
