package tokens.stmts;

import tokens.NonTerminalToken;
import utils.StringHelper;

public final class Stmts implements NonTerminalToken {
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

    private final Stmt stmt;
    private final Stmts stmts;

    private Stmts(Stmt stmt, Stmts stmts) {
        this.stmt = stmt;
        this.stmts = stmts;
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
}
