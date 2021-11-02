public final class IfEnd implements NonTerminalToken {
    public static IfEnd withStmt(Stmt stmt) {
        return new IfEnd(stmt);
    }

    public static IfEnd end() {
        return new IfEnd(null);
    }

    private final Stmt stmt;

    private IfEnd(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String asString(int tabs) {
        if (stmt == null) {
            return "";
        }
        return "else " + stmt.asString(tabs);
    }
}
