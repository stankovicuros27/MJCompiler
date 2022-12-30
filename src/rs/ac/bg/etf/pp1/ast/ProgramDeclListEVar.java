// generated with ast extension for cup
// version 0.8
// 30/11/2022 23:8:13


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclListEVar extends ProgramDeclListE {

    private ProgramDeclListE ProgramDeclListE;
    private VarDecl VarDecl;

    public ProgramDeclListEVar (ProgramDeclListE ProgramDeclListE, VarDecl VarDecl) {
        this.ProgramDeclListE=ProgramDeclListE;
        if(ProgramDeclListE!=null) ProgramDeclListE.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ProgramDeclListE getProgramDeclListE() {
        return ProgramDeclListE;
    }

    public void setProgramDeclListE(ProgramDeclListE ProgramDeclListE) {
        this.ProgramDeclListE=ProgramDeclListE;
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
        if(ProgramDeclListE!=null) ProgramDeclListE.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclListEVar(\n");

        if(ProgramDeclListE!=null)
            buffer.append(ProgramDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclListEVar]");
        return buffer.toString();
    }
}
