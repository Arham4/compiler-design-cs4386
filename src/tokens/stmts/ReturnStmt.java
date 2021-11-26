package tokens.stmts;

import tokens.expr.Expr;
import tokens.lexeme.Type;
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
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        expr.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        return null;
    }
}
