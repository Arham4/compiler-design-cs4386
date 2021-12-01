package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;

import java.util.Map;
import java.util.Objects;

public final class ReturnStmt implements Stmt, Contextualized {
    public static ReturnStmt blank() {
        return new ReturnStmt(null);
    }

    public static ReturnStmt withExpr(Expr expr) {
        return new ReturnStmt(expr);
    }

    private String methodId;
    private final Expr expr;

    private ReturnStmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "return" + (expr == null ? "" : " " + expr.asString(tabs)) + ";";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Objects.requireNonNull(methodId);

        Type methodType = methodSymbolTable.get(methodId).getType();

        if (expr == null) {
            if (methodType != Types.VOID) {
                throw TypeCheckException.withFault("Error: Method " + methodId + " expects return type " + methodType.getType() + ", but received no return type");
            }
        } else {
            Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
            if (!methodType.equals(exprType)) {
                throw TypeCheckException.withFault("Error: Method " + methodId + " expects return type " + methodType.getType() + ", but received return type " + exprType.getType());
            }
        }
        return null;
    }
}
