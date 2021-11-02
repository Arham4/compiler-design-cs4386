public final class ArgDecls implements NonTerminalToken {
    public static ArgDecls of(ArgDeclList argDeclList) {
        return new ArgDecls(argDeclList);
    }

    public static ArgDecls empty() {
        return new ArgDecls(null);
    }

    private final ArgDeclList argDeclList;

    public ArgDecls(ArgDeclList argDeclList) {
        this.argDeclList = argDeclList;
    }

    @Override
    public String asString(int tabs) {
        if (argDeclList == null) {
            return "";
        }
        return argDeclList.asString(tabs);
    }
}
