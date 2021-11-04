package tokens.methods;

import tokens.NonTerminalToken;

public final class MethodDecls implements NonTerminalToken {
    public static final class Builder {
        private MethodDecl methodDecl = null;
        private MethodDecls methodDecls = null;

        public Builder methodDecl(MethodDecl methodDecl) {
            this.methodDecl = methodDecl;
            return this;
        }

        public Builder methodDecls(MethodDecls methodDecls) {
            this.methodDecls = methodDecls;
            return this;
        }

        public MethodDecls build() {
            return new MethodDecls(methodDecl, methodDecls);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final MethodDecl methodDecl;
    private final MethodDecls methodDecls;

    private MethodDecls(MethodDecl methodDecl, MethodDecls methodDecls) {
        this.methodDecl = methodDecl;
        this.methodDecls = methodDecls;
    }

    @Override
    public String asString(int tabs) {
        return (methodDecls == null ? "" : methodDecls.asString(tabs) + "\n") + methodDecl.asString(tabs) + "\n";
    }
}
