package tokens.methods;

import tokens.NonTerminalToken;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

public final class MethodDecls implements NonTerminalToken, TypeCheckable<Void> {
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

    @Override
    public Void typeCheck() throws TypeCheckException {
        methodDecl.typeCheck();
        methodDecls.typeCheck();
        return null;
    }
}
