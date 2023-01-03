package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	
	
	Logger log = Logger.getLogger(getClass());
	
	/****************** error handling ********************/
	
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> linija ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> linija ").append(line);
		log.info(msg.toString());
	}
	
	
	
	
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
	}
	
	public void visit(ProgramDeclListE programDeclListE) {
		
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Type (" + type.getTypeName() + ") not found in Symbol Table", null);
			type.struct = Tab.noType;
		} else {
			
		}
	}
	
}
