package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

public final class IfEnd implements NonTerminalToken, TypeCheckable<Void>, Nestable {
    public static IfEnd withStmt(Stmt stmt) {
        return new IfEnd(stmt);
    }

    public static IfEnd end() {
        return new IfEnd(null);
    }

    private String methodId;
    private final Stmt stmt;

    private IfEnd(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean hasReturnStmt() {
        return stmt instanceof ReturnStmt;
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (stmt != null) {
            if (stmt instanceof Contextualized) {
                ((Contextualized) stmt).setMethodId(methodId);
            }
            stmt.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
