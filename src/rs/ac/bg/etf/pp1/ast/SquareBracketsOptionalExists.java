// generated with ast extension for cup
// version 0.8
// 3/0/2023 19:20:51


package rs.ac.bg.etf.pp1.ast;

public class SquareBracketsOptionalExists extends SquareBracketsOptional {

    public SquareBracketsOptionalExists () {
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
        buffer.append("SquareBracketsOptionalExists(\n");

        buffer.append(tab);
        buffer.append(") [SquareBracketsOptionalExists]");
        return buffer.toString();
    }
}