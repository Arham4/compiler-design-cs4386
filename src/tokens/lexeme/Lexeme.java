package tokens.lexeme;

import tokens.NonTerminalToken;

public final class Lexeme implements NonTerminalToken {
    public static Lexeme of(String lexeme) {
        return new Lexeme(lexeme);
    }

    private final String lexeme;

    private Lexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String asString(int tabs) {
        return lexeme;
    }
}
