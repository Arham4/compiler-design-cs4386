package tokens.stmts;

import tokens.NonTerminalToken;
import tokens.fields.FieldInformation;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

public final class IfEnd implements NonTerminalToken, TypeCheckable<Void> {
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

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (stmt != null) {
            stmt.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
