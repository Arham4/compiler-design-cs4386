public final class MethodInvocationStmt implements Stmt {
    public static MethodInvocationStmt of(String id) {
        return new MethodInvocationStmt(id);
    }

    private final String id;

    private MethodInvocationStmt(String id) {
        this.id = id;
    }

    @Override
    public String asString(int tabs) {
        return id + "();";
    }
}
