package tokens.expr;

import tokens.NonTerminalToken;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

public final class OptionalExpr implements NonTerminalToken, TypeCheckable<Type> {
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

    public boolean isShow() {
        return show;
    }

    @Override
    public String asString(int tabs) {
        if (!show) {
            return "";
        }
        return " = " + expr.asString(tabs);
    }

    @Override
    public Type typeCheck() throws TypeCheckException {
        return expr.typeCheck();
    }
}
