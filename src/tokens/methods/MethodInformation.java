package tokens.methods;

import tokens.lexeme.Type;

public final class MethodInformation {
    public static MethodInformation of(Type type) {
        return new MethodInformation(type);
    }

    private final Type type;

    private MethodInformation(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
