package tokens.fields;

import tokens.expr.OptionalExpr;
import tokens.lexeme.OptionalLexeme;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;

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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (!fieldSymbolTable.containsKey(id)) {
            fieldSymbolTable.put(id, FieldInformation.empty());
        }
        if (fieldSymbolTable.get(id).isAlreadyDeclaredAtScope(scope)) {
            throw redeclarationError(id, scope);
        }
        fieldSymbolTable.get(id).put(scope, type, optionalFinal.isShow());

        if (optionalExpr.isShow()) {
            Type exprType = optionalExpr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
            if (type == Types.INTLIT) {
                if (exprType != Types.INTLIT) {
                    throw conversionError(exprType, "int");
                }
            } else if (type == Types.FLOATLIT) {
                if (exprType != Types.FLOATLIT && exprType != Types.INTLIT) {
                    throw conversionError(exprType, "float");
                }
            } else if (type == Types.BOOLLIT) {
                if (exprType != Types.BOOLLIT && exprType != Types.INTLIT) {
                    throw conversionError(exprType, "bool");
                }
            } else if (type == Types.CHARLIT) {
                if (exprType != Types.CHARLIT) {
                    throw conversionError(exprType, "char");
                }
            }
        }
        return null;
    }
}
