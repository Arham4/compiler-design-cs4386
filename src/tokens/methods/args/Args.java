package tokens.methods.args;

import tokens.NonTerminalToken;
import tokens.expr.Expr;

public final class Args implements NonTerminalToken {
    public static class Builder {
        private Expr expr;
        private Args args = null;

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public Builder args(Args args) {
            this.args = args;
            return this;
        }

        public Args build() {
            return new Args(expr, args);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Expr expr;
    private final Args args;

    Args(Expr expr, Args args) {
        this.expr = expr;
        this.args = args;
    }

    @Override
    public String asString(int tabs) {
        return expr.asString(tabs) + (args == null ? "" : ", " + args.asString(tabs));
    }
}
