package tokens.stmts;

import tokens.expr.Expr;
import tokens.id.Name;

public final class ReassignStmt implements Stmt {
    public static class Builder {
        private Name name;
        private Expr expr;

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public ReassignStmt build() {
            return new ReassignStmt(name, expr);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Name name;
    private final Expr expr;

    private ReassignStmt(Name name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + " = " + expr.asString(tabs) + ";";
    }
}
