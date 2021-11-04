package tokens.stmts;

import tokens.id.Name;

public final class IncrementStmt implements Stmt {
    public static IncrementStmt withName(Name name) {
        return new IncrementStmt(name);
    }

    private final Name name;

    private IncrementStmt(Name name) {
        this.name = name;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + "++;";
    }
}
