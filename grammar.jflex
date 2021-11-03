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
%class Grammar

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

tab_explicit     = \\t
newline_explicit = \\n
tab              = \t
newline          = \n
slash			 = \\
period           = \.
asterisk         = \*
letter           = [A-Za-z]
digit            = [0-9]
char             = {newline_explicit}|{tab_explicit}|{slash}{slash}
char_exceptions  = [[^\\] && [^']]|{slash}'
str_exceptions   = [[[^\\] && [^\"]] &&[[^\n] && [^\t]]]|{slash}\"
id   			 = {letter}+
intlit	         = {digit}+
charlit          = '({char}|{char_exceptions})'
strlit           = \"({char}|{str_exceptions})*\"
floatlit         = {digit}+{period}{digit}+
inlinecomment    = {slash}{slash}.*\n
whitespace       = [ \n\t\r]
multilinecomment = {slash}{asterisk}(([^*\\]|[^\\][*]|[\\][^*])*{whitespace}*){asterisk}{slash}



%%
/**
 * LEXICAL RULES:
 */
read               { return newSym(sym.READ, "read"); }
print		           { return newSym(sym.PRINT, "print"); }
"printline"        { return newSym(sym.PRINTLINE, "printline"); }
"("            { return newSym(sym.LEFT_PARENTHESIS, "("); }
")"            { return newSym(sym.RIGHT_PARENTHESIS, ")"); }
"["            { return newSym(sym.LEFT_SQUARE_PARENTHESIS, "["); }
"]"            { return newSym(sym.RIGHT_SQUARE_PARENTHESIS, "]"); }
"{"            { return newSym(sym.LEFT_BRACKET, "{"); }
"}"            { return newSym(sym.RIGHT_BRACKET, "}"); }
"++"            { return newSym(sym.DOUBLE_PLUS, "++"); }
"--"            { return newSym(sym.DOUBLE_MINUS, "--"); }
"<"            { return newSym(sym.LESS_THAN, "<"); }
">"            { return newSym(sym.GREATER_THAN, ">"); }
"<="            { return newSym(sym.LESS_THAN_OR_EQUAL_TO, "<="); }
">="            { return newSym(sym.GREATER_THAN_OR_EQUAL_TO, ">="); }
"=="            { return newSym(sym.EQUAL_TO, "<>"); }
"<>"            { return newSym(sym.NOT_EQUAL_TO, "<>"); }
"~"            { return newSym(sym.NOT, "~"); }
"?"            { return newSym(sym.TERNARY_IF, "?"); }
":"            { return newSym(sym.TERNARY_ELSE, ":"); }
","            { return newSym(sym.PARAMETER_SEPARATOR, ","); }
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
"return"           { return newSym(sym.RETURN, "return"); }
"void"           { return newSym(sym.RETURN, "void"); }
"final"            { return newSym(sym.FINAL, "final"); }
"int"              { return newSym(sym.INT_PRIMITIVE, "int"); }
"char"             { return newSym(sym.CHAR_PRIMITIVE, "char"); }
"bool"             { return newSym(sym.BOOL_PRIMITIVE, "bool"); }
"float"            { return newSym(sym.FLOAT_PRIMITIVE, "float"); }
"true"               { return newSym(sym.BOOL_TRUE, "true"); }
"false"              { return newSym(sym.BOOL_FALSE, "false"); }
var		             { return newSym(sym.VAR, "var"); }
{id}               { return newSym(sym.ID, yytext()); }
{intlit}           { return newSym(sym.INTLIT, new Integer(yytext())); }
{charlit}          { return newSym(sym.CHARLIT, yytext()); }
{strlit}           { return newSym(sym.STRLIT, yytext()); }
{floatlit}         { return newSym(sym.FLOATLIT, new Double(yytext())); }
{inlinecomment}    { /* For this stand-alone lexer, print out comments. */}
{multilinecomment} { /* Ignore */ }
{whitespace}       { /* Ignore whitespace. */ }
.                  { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + yyline + ", column: " + yychar); }
