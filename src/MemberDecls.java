public final class MemberDecls implements NonTerminalToken {
    public static final class Builder {
        private FieldDecls fieldDecls;
        private MethodDecls methodDecls;

        private Builder() {
        }

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public Builder methodDecls(MethodDecls methodDecls) {
            this.methodDecls = methodDecls;
            return this;
        }

        public MemberDecls build() {
            return new MemberDecls(fieldDecls, methodDecls);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final FieldDecls fieldDecls;
    private final MethodDecls methodDecls;

    private MemberDecls(FieldDecls fieldDecls, MethodDecls methodDecls) {
        this.fieldDecls = fieldDecls;
        this.methodDecls = methodDecls;
    }

    @Override
    public String asString(int tabs) {
        return (fieldDecls == null ? "" : fieldDecls.asString(tabs)) + "\n"
                + (methodDecls == null ? "" : methodDecls.asString(tabs));
    }
}
