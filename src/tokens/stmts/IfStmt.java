package tokens.stmts;

import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import utils.StringHelper;

import java.util.Map;

public final class IfStmt implements Stmt {
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

    private final Expr expr;
    private final Stmt stmt;
    private final IfEnd ifEnd;

    private IfStmt(Expr expr, Stmt stmt, IfEnd ifEnd) {
        this.expr = expr;
        this.stmt = stmt;
        this.ifEnd = ifEnd;
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
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        Type exprType = expr.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
            throw TypeCheckException.withFault("If statement cannot be determined with expression that is not boolean (or implicitly coerced)");
        }
        stmt.typeCheck(scope + 1, variableSymbolTable, methodSymbolTable);
        if (ifEnd.isShow()) {
            ifEnd.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
