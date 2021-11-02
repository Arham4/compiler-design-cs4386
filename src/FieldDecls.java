public final class FieldDecls implements NonTerminalToken {
    public static final class Builder {
        private FieldDecl fieldDecl = null;
        private FieldDecls fieldDecls = null;

        public Builder fieldDecl(FieldDecl fieldDecl) {
            this.fieldDecl = fieldDecl;
            return this;
        }

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public FieldDecls build() {
            return new FieldDecls(fieldDecl, fieldDecls);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final FieldDecl fieldDecl;
    private final FieldDecls fieldDecls;

    private FieldDecls(FieldDecl fieldDecl, FieldDecls fieldDecls) {
        this.fieldDecl = fieldDecl;
        this.fieldDecls = fieldDecls;
    }

    @Override
    public String asString(int tabs) {
        if (fieldDecl == null || fieldDecls == null) {
            return "";
        }
        return fieldDecl.asString(tabs) + "\n" + fieldDecls.asString(tabs);
    }
}
