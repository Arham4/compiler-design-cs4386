package tokens.fields;

import tokens.expr.OptionalExpr;
import tokens.lexeme.OptionalLexeme;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;

import java.util.HashMap;
import java.util.Map;

import static type_checking.TypeCheckException.conversionError;
import static type_checking.TypeCheckException.redeclarationError;

public final class ExpressionFieldDecl implements FieldDecl {
    public static final class Builder {
        private Type type;
        private String id;
        private OptionalLexeme optionalFinal;
        private OptionalExpr optionalExpr;

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder optionalFinal(OptionalLexeme optionalFinal) {
            this.optionalFinal = optionalFinal;
            return this;
        }

        public Builder optionalExpr(OptionalExpr optionalExpr) {
            this.optionalExpr = optionalExpr;
            return this;
        }

        public ExpressionFieldDecl build() {
            return new ExpressionFieldDecl(type, id, optionalFinal, optionalExpr);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Type type;
    private final String id;
    private final OptionalLexeme optionalFinal;
    private final OptionalExpr optionalExpr;

    private ExpressionFieldDecl(Type type, String id, OptionalLexeme optionalFinal, OptionalExpr optionalExpr) {
        this.type = type;
        this.id = id;
        this.optionalFinal = optionalFinal;
        this.optionalExpr = optionalExpr;
    }

    @Override
    public String asString(int tabs) {
        return optionalFinal.asString(tabs) + (optionalFinal.isShow() ? " " : "")
                + type.asString(tabs) + " " + id + optionalExpr.asString(tabs) + ";";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (!variableSymbolTable.containsKey(id)) {
            variableSymbolTable.put(id, new HashMap<>());
        }
        if (variableSymbolTable.get(id).containsKey(scope)) {
            throw redeclarationError(id, scope);
        }
        variableSymbolTable.get(id).put(scope, type);

        if (type == Types.INTLIT) {
            if (optionalExpr.isShow() && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.INTLIT) {
                throw conversionError(optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable), "int");
            }
        } else if (type == Types.FLOATLIT) {
            if (optionalExpr.isShow() && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.FLOATLIT && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.INTLIT) {
                throw conversionError(optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable), "float");
            }
        } else if (type == Types.BOOLLIT) {
            if (optionalExpr.isShow() && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.BOOLLIT && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.INTLIT) {
                throw conversionError(optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable), "bool");
            }
        } else if (type == Types.CHARLIT) {
            if (optionalExpr.isShow() && optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable) != Types.CHARLIT) {
                throw conversionError(optionalExpr.typeCheck(scope, variableSymbolTable, methodSymbolTable), "char");
            }
        }
        return null;
    }
}
