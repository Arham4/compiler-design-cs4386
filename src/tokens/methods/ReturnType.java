package tokens.methods;

import tokens.NonTerminalToken;
import tokens.lexeme.Type;

public final class ReturnType implements NonTerminalToken {
    public static ReturnType withType(Type type) {
        return new ReturnType(type);
    }

    public static ReturnType withoutType() {
        return new ReturnType(null);
    }

    private final Type type;

    private ReturnType(Type type) {
        this.type = type;
    }

    @Override
    public String asString(int tabs) {
        if (type == null) {
            return "void";
        }
        return type.asString(tabs);
    }
}
