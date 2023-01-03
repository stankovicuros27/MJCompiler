// generated with ast extension for cup
// version 0.8
// 3/0/2023 17:26:13


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclListEClass extends ProgramDeclListE {

    private ProgramDeclListE ProgramDeclListE;
    private ClassDecl ClassDecl;

    public ProgramDeclListEClass (ProgramDeclListE ProgramDeclListE, ClassDecl ClassDecl) {
        this.ProgramDeclListE=ProgramDeclListE;
        if(ProgramDeclListE!=null) ProgramDeclListE.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public ProgramDeclListE getProgramDeclListE() {
        return ProgramDeclListE;
    }

    public void setProgramDeclListE(ProgramDeclListE ProgramDeclListE) {
        this.ProgramDeclListE=ProgramDeclListE;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramDeclListE!=null) ProgramDeclListE.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclListEClass(\n");

        if(ProgramDeclListE!=null)
            buffer.append(ProgramDeclListE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclListEClass]");
        return buffer.toString();
    }
}
