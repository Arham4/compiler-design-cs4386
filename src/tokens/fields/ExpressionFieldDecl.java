package tokens.fields;

import tokens.expr.OptionalExpr;
import tokens.lexeme.OptionalLexeme;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;

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
    public Void typeCheck() throws TypeCheckException {
        return null;
    }
}
