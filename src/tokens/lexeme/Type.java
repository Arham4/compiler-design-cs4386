package tokens.lexeme;

import tokens.NonTerminalToken;

public final class Type implements NonTerminalToken {
    public static Type of(String type) {
        return new Type(type, false);
    }

    private final String type;
    private final boolean array;

    private Type(String type, boolean array) {
        this.type = type;
        this.array = array;
    }

    public String getType() {
        return type;
    }

    public Type asArray() {
        return new Type(type, true);
    }

    public boolean isArray() {
        return array;
    }

    @Override
    public String asString(int tabs) {
        return type;
    }
}
