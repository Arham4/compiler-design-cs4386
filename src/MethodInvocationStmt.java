public final class MethodInvocationStmt implements Stmt {
    public static MethodInvocationStmt blank(String id) {
        return new MethodInvocationStmt(id, null);
    }

    public static MethodInvocationStmt withArgs(String id, Args args) {
        return new MethodInvocationStmt(id, args);
    }

    private final String id;
    private final Args args;

    private MethodInvocationStmt(String id, Args args) {
        this.id = id;
        this.args = args;
    }

    @Override
    public String asString(int tabs) {
        return id + "(" + (args == null ? "" : args.asString(tabs)) + ");";
    }
}
