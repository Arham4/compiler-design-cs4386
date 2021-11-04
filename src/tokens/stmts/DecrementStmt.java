package tokens.stmts;

import tokens.id.Name;

public final class DecrementStmt implements Stmt {
    public static DecrementStmt withName(Name name) {
        return new DecrementStmt(name);
    }

    private final Name name;

    private DecrementStmt(Name name) {
        this.name = name;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + "--;";
    }
}
