public final class ArgDeclList implements NonTerminalToken {
    public static class Builder {
        private ArgDecl argDecl = null;
        private ArgDeclList argDeclList = null;

        public Builder argDecl(ArgDecl argDecl) {
            this.argDecl = argDecl;
            return this;
        }

        public Builder argDeclList(ArgDeclList argDeclList) {
            this.argDeclList = argDeclList;
            return this;
        }

        public ArgDeclList build() {
            return new ArgDeclList(argDecl, argDeclList);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final ArgDecl argDecl;
    private final ArgDeclList argDeclList;

    public ArgDeclList(ArgDecl argDecl, ArgDeclList argDeclList) {
        this.argDecl = argDecl;
        this.argDeclList = argDeclList;
    }

    @Override
    public String asString(int tabs) {
        return argDecl.asString(tabs) + (argDeclList == null ? "" : ", " + argDeclList.asString(tabs));
    }
}
