package tokens.fields;

import tokens.lexeme.Lexeme;
import tokens.expr.OptionalExpr;
import tokens.lexeme.OptionalLexeme;

public final class ExpressionFieldDecl implements FieldDecl {
    public static final class Builder {
        private Lexeme type;
        private String id;
        private OptionalLexeme optionalFinal;
        private OptionalExpr optionalExpr;

        public Builder type(Lexeme type) {
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

    private final Lexeme type;
    private final String id;
    private final OptionalLexeme optionalFinal;
    private final OptionalExpr optionalExpr;

    private ExpressionFieldDecl(Lexeme type, String id, OptionalLexeme optionalFinal, OptionalExpr optionalExpr) {
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
}
