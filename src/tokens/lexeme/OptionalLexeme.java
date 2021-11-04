package tokens.lexeme;

import tokens.NonTerminalToken;

public final class OptionalLexeme implements NonTerminalToken {
    public static OptionalLexeme shown(Lexeme lexeme) {
        return new OptionalLexeme(lexeme, true);
    }

    public static OptionalLexeme hidden() {
        return new OptionalLexeme(null, false);
    }

    private final Lexeme lexeme;
    private final boolean show;

    private OptionalLexeme(Lexeme lexeme, boolean show) {
        this.lexeme = lexeme;
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    @Override
    public String asString(int tabs) {
        return show ? lexeme.asString(tabs) : "";
    }
}
