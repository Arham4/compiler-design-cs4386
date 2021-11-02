public final class ReturnStmt implements Stmt {
    public static ReturnStmt blank() {
        return new ReturnStmt(null);
    }

    public static ReturnStmt withExpr(Expr expr) {
        return new ReturnStmt(expr);
    }

    private final Expr expr;

    private ReturnStmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String asString(int tabs) {
        return "return" + (expr == null ? "" : " " + expr.asString(tabs)) + ";";
    }
}
