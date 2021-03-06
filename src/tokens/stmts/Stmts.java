package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

import static utils.SymbolTableHelper.removeScopeFromSymbolTable;

public final class Stmts implements NonTerminalToken, TypeCheckable<Void>, Nestable {
    public static final class Builder {
        private Stmt stmt = null;
        private Stmts stmts = null;

        public Builder stmt(Stmt stmt) {
            this.stmt = stmt;
            return this;
        }

        public Builder stmts(Stmts stmts) {
            this.stmts = stmts;
            return this;
        }

        public Stmts build() {
            return new Stmts(stmt, stmts);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String methodId;
    private final Stmt stmt;
    private final Stmts stmts;

    private Stmts(Stmt stmt, Stmts stmts) {
        this.stmt = stmt;
        this.stmts = stmts;
    }

    @Override
    public boolean hasReturnStmt() {
        if (stmt instanceof ReturnStmt) {
            return true;
        }
        Stmts currentStmts = stmts;
        while (currentStmts != null) {
            if (currentStmts.stmt instanceof ReturnStmt) {
                return true;
            }
            currentStmts = stmts.stmts;
        }
        return false;
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    @Override
    public String asString(int tabs) {
        return (stmts == null ? "" : stmts.asString(tabs)) + stmt.asString(StringHelper.tabs(tabs), tabs) + "\n";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (stmts != null) {
            stmts.setMethodId(methodId);
            stmts.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        if (stmt instanceof Contextualized) {
            ((Contextualized) stmt).setMethodId(methodId);
        }
        stmt.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        removeScopeFromSymbolTable(scope + 1, fieldSymbolTable);
        return null;
    }
}
