package tokens.lexeme;

import tokens.NonTerminalToken;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return array == type1.array && Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, array);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Type{");
        sb.append("type='").append(type).append('\'');
        sb.append(", array=").append(array);
        sb.append('}');
        return sb.toString();
    }
}
