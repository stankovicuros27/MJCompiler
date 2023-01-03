// generated with ast extension for cup
// version 0.8
// 3/0/2023 2:22:1


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclListEVar extends ProgramDeclListE {

    private ProgramDeclListE ProgramDeclListE;
    private VarDeclGlobal VarDeclGlobal;

    public ProgramDeclListEVar (ProgramDeclListE ProgramDeclListE, VarDeclGlobal VarDeclGlobal) {
        this.ProgramDeclListE=ProgramDeclListE;
        if(ProgramDeclListE!=null) ProgramDeclListE.setParent(this);
        this.VarDeclGlobal=VarDeclGlobal;
        if(VarDeclGlobal!=null) VarDeclGlobal.setParent(this);
    }

    public ProgramDeclListE getProgramDeclListE() {
        return ProgramDeclListE;
    }

    public void setProgramDeclListE(ProgramDeclListE ProgramDeclListE) {
        this.ProgramDeclListE=ProgramDeclListE;
    }

    public VarDeclGlobal getVarDeclGlobal() {
        return VarDeclGlobal;
    }

    public void setVarDeclGlobal(VarDeclGlobal VarDeclGlobal) {
        this.VarDeclGlobal=VarDeclGlobal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.accept(visitor);
        if(VarDeclGlobal!=null) VarDeclGlobal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseTopDown(visitor);
        if(VarDeclGlobal!=null) VarDeclGlobal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseBottomUp(visitor);
        if(VarDeclGlobal!=null) VarDeclGlobal.traverseBottomUp(visitor);
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

        if(VarDeclGlobal!=null)
            buffer.append(VarDeclGlobal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclListEVar]");
        return buffer.toString();
    }
}
