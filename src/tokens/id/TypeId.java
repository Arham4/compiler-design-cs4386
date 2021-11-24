package tokens.id;

import tokens.lexeme.Lexeme;
import tokens.lexeme.Type;

public final class TypeId {
    public static TypeId of(Type type, String id) {
        return new TypeId(type, id);
    }

    private final Type type;
    private final String id;

    private TypeId(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
