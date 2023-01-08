// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:34:55


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclListEConst extends ProgramDeclListE {

    private ProgramDeclListE ProgramDeclListE;
    private ConstDecl ConstDecl;

    public ProgramDeclListEConst (ProgramDeclListE ProgramDeclListE, ConstDecl ConstDecl) {
        this.ProgramDeclListE=ProgramDeclListE;
        if(ProgramDeclListE!=null) ProgramDeclListE.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ProgramDeclListE getProgramDeclListE() {
        return ProgramDeclListE;
    }

    public void setProgramDeclListE(ProgramDeclListE ProgramDeclListE) {
        this.ProgramDeclListE=ProgramDeclListE;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclListEConst(\n");

        if(ProgramDeclListE!=null)
            buffer.append(ProgramDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclListEConst]");
        return buffer.toString();
    }
}
