package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import utils.StringHelper;

import java.util.Map;

public final class WhileStmt implements Stmt, Nestable {
    public static class Builder {
        private Expr expr;
        private Stmt stmt;

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public Builder stmt(Stmt stmt) {
            this.stmt = stmt;
            return this;
        }

        public WhileStmt build() {
            return new WhileStmt(expr, stmt);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String methodId;
    private final Expr expr;
    private final Stmt stmt;

    private WhileStmt(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public boolean hasReturnStmt() {
        return stmt instanceof ReturnStmt;
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    @Override
    public String asString(String prefix, int tabs) {
        if (stmt instanceof BodyStmt) {
            return prefix + "while (" + expr.asString(tabs) + ") " + stmt.asString("", tabs);
        } else {
            return prefix + "while (" + expr.asString(tabs) + ")\n" + stmt.asString(StringHelper.tabs(tabs + 1), tabs + 1);
        }
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
            throw TypeCheckException.withFault("Error: While statement cannot be determined with expression that is not boolean (or implicitly coerced)");
        }
        if (stmt instanceof Contextualized) {
            ((Contextualized) stmt).setMethodId(methodId);
        }
        stmt.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
