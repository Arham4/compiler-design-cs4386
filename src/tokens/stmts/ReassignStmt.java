package tokens.stmts;

import tokens.expr.Expr;
import tokens.fields.FieldInformation;
import tokens.id.Name;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;
import utils.Pair;

import java.util.Map;

import static type_checking.TypeCheckException.conversionError;

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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Pair<Type, Boolean> closestScopeInfo = name.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        Type closestScopeType = closestScopeInfo.getFirst();
        boolean closestScopeIsFinal = closestScopeInfo.getSecond();
        Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);

        if (closestScopeIsFinal) {
            throw TypeCheckException.withFault("Error: Final variables cannot be reassigned.");
        } else if (closestScopeType == Types.INTLIT) {
            if (exprType != Types.INTLIT) {
                throw conversionError(exprType, "int");
            }
        } else if (closestScopeType == Types.FLOATLIT) {
            if (exprType != Types.FLOATLIT && exprType != Types.INTLIT) {
                throw conversionError(exprType, "float");
            }
        } else if (closestScopeType == Types.BOOLLIT) {
            if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
                throw conversionError(exprType, "bool");
            }
        } else if (closestScopeType == Types.CHARLIT) {
            if (exprType != Types.CHARLIT) {
                throw conversionError(exprType, "char");
            }
        } else {
            throw TypeCheckException.withFault("Error: incompatible types");
        }
        return null;
    }
}
