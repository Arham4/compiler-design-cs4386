public final class BodyStmt implements Stmt {
    public static class Builder {
        private FieldDecls fieldDecls;
        private Stmts stmts;
        private OptionalLexeme optionalSemi;

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public Builder stmts(Stmts stmts) {
            this.stmts = stmts;
            return this;
        }

        public Builder optionalSemi(OptionalLexeme optionalSemi) {
            this.optionalSemi = optionalSemi;
            return this;
        }

        public BodyStmt build() {
            return new BodyStmt(fieldDecls, stmts, optionalSemi);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final FieldDecls fieldDecls;
    private final Stmts stmts;
    private final OptionalLexeme optionalSemi;

    private BodyStmt(FieldDecls fieldDecls, Stmts stmts, OptionalLexeme optionalSemi) {
        this.fieldDecls = fieldDecls;
        this.stmts = stmts;
        this.optionalSemi = optionalSemi;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "{\n"
                + (fieldDecls == null ? "" : fieldDecls.asString(tabs + 1))
                + stmts.asString(tabs + 1)
                + StringHelper.withTabs(tabs, "}" + optionalSemi.asString(tabs));
    }
}
