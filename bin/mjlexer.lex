// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ IMPORTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;



%%
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ DIRECTIVES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

%{
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline + 1, yycolumn);
	}
	
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline + 1, yycolumn, value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}



%%
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ REGULAR EXPRESSIONS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


// BLANK

" " 		{ }
"\b" 		{ }
"\t" 		{ }
"\r\n" 		{ }
"\f" 		{ }


// KEYWORDS

"program"   { return new_symbol(sym.PROGRAM, yytext()); }
"break" 	{ return new_symbol(sym.BREAK, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"if" 		{ return new_symbol(sym.IF, yytext()); }
"while" 	{ return new_symbol(sym.WHILE, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"foreach"	{ return new_symbol(sym.FOREACH, yytext()); }


// OPERATORS

"+"   		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.EQUALS, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUALS, yytext()); }
">" 		{ return new_symbol(sym.GREATER, yytext()); }
">=" 		{ return new_symbol(sym.GREATER_EQUALS, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
"<=" 		{ return new_symbol(sym.LESS_EQUALS, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"="			{ return new_symbol(sym.ASSIGN, yytext()); }
"++"		{ return new_symbol(sym.INC, yytext()); }
"--"		{ return new_symbol(sym.DEC, yytext()); }
";" 		{ return new_symbol(sym.SEMI_COLON, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"("			{ return new_symbol(sym.L_PAREN, yytext()); }
")"			{ return new_symbol(sym.R_PAREN, yytext()); }
"["			{ return new_symbol(sym.L_SQUARE_BRACKET, yytext()); }
"]"			{ return new_symbol(sym.R_SQUARE_BRACKET, yytext()); }
"{"			{ return new_symbol(sym.L_BRACE, yytext()); }
"}"			{ return new_symbol(sym.R_BRACE, yytext()); }
"=>"		{ return new_symbol(sym.ARROW, yytext()); }


// COMMENTS

"//"				{ yybegin(COMMENT); }
<COMMENT> . 		{ yybegin(COMMENT); }
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }


// TOKEN_TYPES

"'"[\040-\176]"'" 				{ return new_symbol(sym.CHAR_CONST, Character.valueOf(yytext().charAt(1))); }
("false"|"true")				{ return new_symbol(sym.BOOL_CONST, Boolean.valueOf(yytext())); }
[0-9]+  						{ return new_symbol(sym.NUM_CONST, Integer.valueOf(yytext())); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 		{ return new_symbol(sym.IDENT, yytext()); }


// ERROR

. { System.err.println("Lexical analysis error (" + yytext() + ") at line " + (yyline + 1)); }

