package tokens.expr;

import tokens.NonTerminalToken;
import tokens.fields.FieldInformation;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

public final class BinaryOp implements NonTerminalToken, TypeCheckable<Type> {
    public static BinaryOp of(Expr expr1, String operation, Expr expr2) {
        return new BinaryOp(expr1, operation, expr2);
    }

    private final Expr expr1;
    private final String operation;
    private final Expr expr2;

    private BinaryOp(Expr expr1, String operation, Expr expr2) {
        this.expr1 = expr1;
        this.operation = operation;
        this.expr2 = expr2;
    }

    @Override
    public String asString(int tabs) {
        return "(" + expr1.asString(tabs) + " " + operation + " " + expr2.asString(tabs) + ")";
    }

    @Override
    public Type typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        Type expr1Type = expr1.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        Type expr2Type = expr2.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
            if (expr1Type != Types.INTLIT && expr1Type != Types.FLOATLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on ints and floats");
            }
            if (expr2Type != Types.INTLIT && expr2Type != Types.FLOATLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on ints and floats");
            }
            if (expr1Type == Types.FLOATLIT) {
                return expr1Type;
            }
            if (expr2Type == Types.FLOATLIT) {
                return expr2Type;
            }
            return expr1Type;
        } else if (operation.equals(">") || operation.equals("<") || operation.equals(">=") || operation.equals("<=") || operation.equals("==") || operation.equals("<>")) {
            if (expr1Type != Types.INTLIT && expr1Type != Types.FLOATLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on ints and floats");
            }
            if (expr2Type != Types.INTLIT && expr2Type != Types.FLOATLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on ints and floats");
            }
            return Types.BOOLLIT;
        } else {
            if (expr1Type != Types.BOOLLIT && expr1Type != Types.INTLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on bools (or implicitly coerced)");
            }
            if (expr2Type != Types.BOOLLIT && expr2Type != Types.INTLIT) {
                throw TypeCheckException.withFault("Error: Binary operation " + operation + " can only be performed on bools (or implicitly coerced)");
            }
            return Types.BOOLLIT;
        }
    }
}
