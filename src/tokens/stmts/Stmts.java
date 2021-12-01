package tokens.stmts;

import tokens.NonTerminalToken;
import tokens.fields.FieldInformation;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

import static utils.SymbolTableHelper.removeScopeFromSymbolTable;

public final class Stmts implements NonTerminalToken, TypeCheckable<Void> {
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

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public boolean isShow() {
        return stmt != null && stmts != null;
    }

    @Override
    public String asString(int tabs) {
        if (stmt == null || stmts == null) {
            return "";
        }
        return stmt.asString(StringHelper.tabs(tabs), tabs) + "\n" + stmts.asString(tabs);
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (stmt != null) {
            if (stmt instanceof TerminatingStmt) {
                ((TerminatingStmt) stmt).setMethodId(methodId);
            }
            stmt.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
            removeScopeFromSymbolTable(scope + 1, fieldSymbolTable);
        }
        if (stmts != null) {
            stmts.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
