package tokens.stmts;

import tokens.expr.Expr;
import tokens.fields.FieldInformation;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;

import java.util.Map;

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
    public String asString(String prefix, int tabs) {
        return prefix + "return" + (expr == null ? "" : " " + expr.asString(tabs)) + ";";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
