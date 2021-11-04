package tokens.stmts;

import tokens.NonTerminalToken;
import utils.StringHelper;

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

    public boolean isShow() {
        return stmt != null;
    }

    @Override
    public String asString(int tabs) {
        if (stmt == null) {
            return "";
        }
        if (stmt instanceof BodyStmt || stmt instanceof IfStmt) {
            return "else " + stmt.asString("", tabs);
        } else {
            return "else\n" + stmt.asString(StringHelper.tabs(tabs + 1), tabs + 1);
        }
    }
}
