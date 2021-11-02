public final class OptionalLexeme implements NonTerminalToken {
    public static OptionalLexeme shown(String lexeme) {
        return new OptionalLexeme(lexeme, true);
    }

    public static OptionalLexeme hidden() {
        return new OptionalLexeme("", false);
    }

    private final String lexeme;
    private final boolean show;

    private OptionalLexeme(String lexeme, boolean show) {
        this.lexeme = lexeme;
        this.show = show;
    }
    @Override
    public String asString(int tabs) {
        return show ? lexeme : "";
    }
}
