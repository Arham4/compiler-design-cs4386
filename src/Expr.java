public interface Expr extends NonTerminalToken {
    static Expr simple(Name name) {
        return name::asString;
    }

    static Expr simpleInvocation(String id) {
        return tabs -> id + "()";
    }

    static Expr invocation(String id, Args args) {
        return tabs -> id + "(" + args.asString(tabs) + ")";
    }

    static Expr intlit(int integer) {
        return tabs -> integer + "";
    }

    static Expr charlit(String character) {
        return tabs -> "'" + StringHelper.escapeJava(character.charAt(0) + "") + "'";
    }

    static Expr strlit(String string) {
        return tabs -> string;
    }

    static Expr floatlit(double floatingPoint) {
        return tabs -> floatingPoint + "";
    }

    static Expr boollit(boolean value) {
        return tabs -> value + "";
    }

    static Expr parenthesized(Expr expr) {
        return tabs -> "(" + expr.asString(tabs) + ")";
    }

    static Expr not(Expr expr) {
        return tabs -> "~" + expr.asString(tabs);
    }

    static Expr minus(Expr expr) {
        return tabs -> "-" + expr.asString(tabs);
    }

    static Expr plus(Expr expr) {
        return tabs -> "+" + expr.asString(tabs);
    }

    static Expr casting(Lexeme type, Expr expr) {
        return tabs -> "(" + type.asString(tabs) + ") " + expr.asString(tabs);
    }

    static Expr binaryOp(Expr expr1, Lexeme binaryOp, Expr expr2) {
        return tabs -> expr1.asString(tabs) + " " + binaryOp.asString(tabs) + " " + expr2.asString(tabs);
    }

    static Expr ternary(Expr expr, Expr exprTrue, Expr exprFalse) {
        return tabs -> "(" + expr.asString(tabs) + ") ? " + exprTrue.asString(tabs) + " : " + exprFalse.asString(tabs) + ")";
    }
}
