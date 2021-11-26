package tokens.stmts;

import tokens.expr.Expr;
import tokens.id.Name;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;
import static utils.SymbolTableHelper.*;

public final class ReassignStmt implements Stmt {
    public static class Builder {
        private Name name;
        private Expr expr;

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public ReassignStmt build() {
            return new ReassignStmt(name, expr);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Name name;
    private final Expr expr;

    private ReassignStmt(Name name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + " = " + expr.asString(tabs) + ";";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (!variableSymbolTable.containsKey(name.getId()) || isScopeTooHigh(scope, variableSymbolTable.get(name.getId()))) {
            throw undeclaredError(name.getId());
        }

        Type exprType = expr.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        if (isNotSameType(scope, variableSymbolTable.get(name.getId()), exprType)) {
            Type closestScopeType = getClosestScopeType(scope, variableSymbolTable.get(name.getId()), exprType);
            String typeAsString = closestScopeType != null ? closestScopeType.getType() : "null";

            throw TypeCheckException.withFault(name.getId() + " attempting to be reassigned with type " + exprType.getType() + " when type of " + name.getId() + " is " + typeAsString);
        }
        return null;
    }
}
