package tokens.expr;

import tokens.NonTerminalToken;
import tokens.id.Name;
import tokens.lexeme.Lexeme;
import tokens.lexeme.Type;
import tokens.methods.args.Args;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

public interface Expr extends NonTerminalToken, TypeCheckable<Type> {
    static Expr simple(Name name) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return name.asString(tabs);
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr simpleInvocation(String id) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return id + "()";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr invocation(String id, Args args) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return id + "(" + args.asString(tabs) + ")";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr intlit(int integer) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return integer + "";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr charlit(String character) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return character;
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr strlit(String string) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return string;
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr floatlit(double floatingPoint) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return floatingPoint + "";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr boollit(boolean value) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return value + "";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr parenthesized(Expr expr) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "(" + expr.asString(tabs) + ")";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr not(Expr expr) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "~" + expr.asString(tabs);
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr minus(Expr expr) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "-" + expr.asString(tabs);
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr plus(Expr expr) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "+" + expr.asString(tabs);
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr casting(Type type, Expr expr) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "(" + type.asString(tabs) + ") " + expr.asString(tabs);
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr binaryOp(Expr expr1, Lexeme binaryOp, Expr expr2) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "(" + expr1.asString(tabs) + " " + binaryOp.asString(tabs) + " " + expr2.asString(tabs) + ")";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }

    static Expr ternary(Expr expr, Expr exprTrue, Expr exprFalse) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return "(" + expr.asString(tabs) + ") ? " + exprTrue.asString(tabs) + " : " + exprFalse.asString(tabs) + ")";
            }

            @Override
            public Type typeCheck() throws TypeCheckException {
                return null;
            }
        };
    }
}
