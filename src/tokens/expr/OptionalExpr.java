package tokens.expr;

import tokens.NonTerminalToken;

public final class OptionalExpr implements NonTerminalToken {
    public static OptionalExpr shownExpr(Expr expr) {
        return new OptionalExpr(expr, true);
    }

    public static OptionalExpr hidden() {
        return new OptionalExpr(null, false);
    }

    private final Expr expr;
    private final boolean show;

    private OptionalExpr(Expr expr, boolean show) {
        this.expr = expr;
        this.show = show;
    }

    @Override
    public String asString(int tabs) {
        if (!show) {
            return "";
        }
        return " = " + expr.asString(tabs);
    }
}
