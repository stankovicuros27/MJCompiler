// generated with ast extension for cup
// version 0.8
// 10/0/2023 21:21:20


package rs.ac.bg.etf.pp1.ast;

public class PrintNumConstOptionalExists extends PrintNumConstOptional {

    private Integer numConst;

    public PrintNumConstOptionalExists (Integer numConst) {
        this.numConst=numConst;
    }

    public Integer getNumConst() {
        return numConst;
    }

    public void setNumConst(Integer numConst) {
        this.numConst=numConst;
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
        buffer.append("PrintNumConstOptionalExists(\n");

        buffer.append(" "+tab+numConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintNumConstOptionalExists]");
        return buffer.toString();
    }
}
