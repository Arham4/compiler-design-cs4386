package tokens.expr;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import tokens.id.Name;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.args.Args;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;

public interface Expr extends NonTerminalToken, TypeCheckable<Type> {
    static Expr simple(Name name) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return name.asString(tabs);
            }

            @Override
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return name.typeCheck(scope, fieldSymbolTable, methodSymbolTable).getFirst();
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                if (!methodSymbolTable.containsKey(id)) {
                    throw undeclaredError(id);
                }
                if (methodSymbolTable.get(id).isArgsNotValid(null, scope, fieldSymbolTable, methodSymbolTable)) {
                    throw TypeCheckException.withFault("Error: Method invocation arguments are invalid for " + asString(0));
                }
                return methodSymbolTable.get(id).getType();
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                if (!methodSymbolTable.containsKey(id)) {
                    throw undeclaredError(id);
                }
                if (methodSymbolTable.get(id).isArgsNotValid(args, scope, fieldSymbolTable, methodSymbolTable)) {
                    throw TypeCheckException.withFault("Error: Method invocation arguments are invalid for " + asString(0));
                }
                return methodSymbolTable.get(id).getType();
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return Types.INTLIT;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return Types.CHARLIT;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return Types.STR;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return Types.FLOATLIT;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return Types.BOOLLIT;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
                    throw TypeCheckException.withFault("Error: Not operation only applicable on bool (or coerced to bool)");
                }
                return Types.BOOLLIT;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                if (exprType != Types.INTLIT && exprType != Types.FLOATLIT) {
                    throw TypeCheckException.withFault("Error: Negative operation only applicable on int or float");
                }
                return exprType;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                if (exprType != Types.INTLIT && exprType != Types.FLOATLIT) {
                    throw TypeCheckException.withFault("Error: Positive operation only applicable on int or float");
                }
                return exprType;
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                if (exprType == Types.INTLIT && type != Types.INTLIT && type != Types.BOOLLIT && type != Types.FLOATLIT) {
                    throw TypeCheckException.withFault("Error: Can't cast type " + type.getType() + " to type int");
                } else if (exprType == Types.FLOATLIT && type != Types.FLOATLIT) {
                    throw TypeCheckException.withFault("Error: Can't cast type " + type.getType() + " to type float");
                } else if (exprType == Types.BOOLLIT && type != Types.BOOLLIT) {
                    throw TypeCheckException.withFault("Error: Can't cast type " + type.getType() + " to type bool");
                } else if (exprType == Types.CHARLIT && type != Types.CHARLIT) {
                    throw TypeCheckException.withFault("Error: Can't cast type " + type.getType() + " to type char");
                } else if (!exprType.isArray() && type == Types.STR) {
                    throw TypeCheckException.withFault("Error: Can't cast array to type String");
                }
                return type;
            }
        };
    }

    static Expr binaryOp(BinaryOp binaryOp) {
        return new Expr() {
            @Override
            public String asString(int tabs) {
                return binaryOp.asString(tabs);
            }

            @Override
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                return binaryOp.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
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
            public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                Type exprTrueType = exprTrue.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                Type exprFalseType = exprFalse.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
                if (exprType != Types.BOOLLIT) {
                    throw TypeCheckException.withFault("Error: Ternary can only be performed on bool expr");
                }
                if (!exprTrueType.equals(exprFalseType)) {
                    throw TypeCheckException.withFault("Error: Ternary must have each conditional be the same type");
                }
                return exprTrueType;
            }
        };
    }
}
