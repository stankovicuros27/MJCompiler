package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class RuleVisitor extends VisitorAdaptor {
	
	int printCnt = 0;
	int varDeclCount = 0;
	
    public void visit(PrintStmt PrintStmt) {
    	printCnt++;
    }

    public void visit(VarDecl VarDecl) {
    	varDeclCount++;
    }

}
