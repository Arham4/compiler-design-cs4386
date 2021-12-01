package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import utils.StringHelper;

import java.util.Map;

public final class IfStmt implements Stmt, Nestable {
    public static class Builder {
        private Expr expr;
        private Stmt stmt;
        private IfEnd ifEnd;

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public Builder stmt(Stmt stmt) {
            this.stmt = stmt;
            return this;
        }

        public Builder ifEnd(IfEnd ifEnd) {
            this.ifEnd = ifEnd;
            return this;
        }

        public IfStmt build() {
            return new IfStmt(expr, stmt, ifEnd);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String methodId;
    private final Expr expr;
    private final Stmt stmt;
    private final IfEnd ifEnd;

    private IfStmt(Expr expr, Stmt stmt, IfEnd ifEnd) {
        this.expr = expr;
        this.stmt = stmt;
        this.ifEnd = ifEnd;
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
            return prefix + "if (" + expr.asString(tabs) + ") " + stmt.asString("", tabs) + (ifEnd.isShow() ? "\n" + StringHelper.withTabs(tabs, ifEnd.asString(tabs)) : "");
        } else {
            return prefix + "if (" + expr.asString(tabs) + ")\n" + stmt.asString(StringHelper.tabs(tabs + 1), tabs + 1) + (ifEnd.isShow() ? "\n" + StringHelper.withTabs(tabs, ifEnd.asString(tabs)) : "");
        }
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
            throw TypeCheckException.withFault("Error: If statement cannot be determined with expression that is not boolean (or implicitly coerced)");
        }
        if (stmt instanceof Contextualized) {
            ((Contextualized) stmt).setMethodId(methodId);
        }
        stmt.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        if (ifEnd.isShow()) {
            ifEnd.setMethodId(methodId);
            ifEnd.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
