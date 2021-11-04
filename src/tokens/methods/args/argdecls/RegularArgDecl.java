package tokens.methods.args.argdecls;

import tokens.lexeme.Lexeme;

public final class RegularArgDecl implements ArgDecl {
    public static class Builder {
        private Lexeme type;
        private String id;

        public Builder type(Lexeme type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public RegularArgDecl build() {
            return new RegularArgDecl(type, id);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Lexeme type;
    private final String id;

    public RegularArgDecl(Lexeme type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String asString(int tabs) {
        return type.asString(tabs) + " " + id;
    }
}
