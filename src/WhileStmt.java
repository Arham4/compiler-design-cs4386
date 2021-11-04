public final class WhileStmt implements Stmt {
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

    private final Expr expr;
    private final Stmt stmt;

    private WhileStmt(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public String asString(String prefix, int tabs) {
        if (stmt instanceof BodyStmt) {
            return prefix + "while (" + expr.asString(tabs) + ") " + stmt.asString("", tabs);
        } else {
            return prefix + "while (" + expr.asString(tabs) + ")\n" + stmt.asString(StringHelper.tabs(tabs + 1), tabs + 1);
        }
    }
}
