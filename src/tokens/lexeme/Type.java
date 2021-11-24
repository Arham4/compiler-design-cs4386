package tokens.lexeme;

import tokens.NonTerminalToken;

public final class Type implements NonTerminalToken {
    public static Type of(String type) {
        return new Type(type);
    }

    private final String type;

    private Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String asString(int tabs) {
        return type;
    }
}
