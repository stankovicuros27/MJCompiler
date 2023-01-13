// generated with ast extension for cup
// version 0.8
// 13/0/2023 16:9:0


package rs.ac.bg.etf.pp1.ast;

public class ProgramDeclListEEmpty extends ProgramDeclListE {

    public ProgramDeclListEEmpty () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDeclListEEmpty(\n");

        buffer.append(tab);
        buffer.append(") [ProgramDeclListEEmpty]");
        return buffer.toString();
    }
}
