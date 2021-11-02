/*-***
 * This grammar is defined for the example grammar defined in the
 *project part 1 instructions
 */

/*
 * NOTE: make sure that the java cup runtime file is in the same directory as this file
 * it is also alright if the runtime location is added to to the classpath, but just
 * putting in the same file is far easier.
 */
import java_cup.runtime.*;


%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class ExampleLexer
/*
 * NOTE: the above name ExampleLexer, will have to be changed here if
 * you chose to rename the lexer object.
 */

%{

/**
 * Return a new Symbol with the given token id, and with the current line and
 * column numbers.
 */
Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

/**
 * Return a new Symbol with the given token id, the current line and column
 * numbers, and the given token value.  The value is used for tokens such as
 * identifiers and numbers.
 */
Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */

tab           = \\t
newline		    = \\n
slash			    = \\
letter        = [A-Za-z]
digit         = [0-9]
id   			    = {letter}+
intlit	      = {digit}+
inlinecomment = {slash}{slash}.*\n
whitespace    = [ \n\t\r]



%%
/**
 * LEXICAL RULES:
 */
read               { return newSym(sym.READ, "read"); }
print		           { return newSym(sym.PRINT, "print"); }
"*"                { return newSym(sym.TIMES, "*"); }
"+"                { return newSym(sym.PLUS, "+"); }
"-"                { return newSym(sym.MINUS, "-"); }
"/"                { return newSym(sym.DIVIDE, "/"); }
"="                { return newSym(sym.ASSMNT, "="); }
";"                { return newSym(sym.SEMI, ";"); }
"class"            { return newSym(sym.CLASS, "class"); }
"&&"               { return newSym(sym.DOUBLE_AND, "&&"); }
"||"               { return newSym(sym.DOUBLE_OR, "||"); }
"else"             { return newSym(sym.ELSE, "else"); }
"if"               { return newSym(sym.IF, "if"); }
"while"            { return newSym(sym.WHILE, "while"); }
var		             { return newSym(sym.VAR, "var"); }
{id}               { return newSym(sym.ID, yytext()); }
{intlit}           { return newSym(sym.INTLIT, new Integer(yytext())); }
{inlinecomment}    { /* For this stand-alone lexer, print out comments. */}
{whitespace}       { /* Ignore whitespace. */ }
.                  { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + yyline + ", column: " + yychar); }
