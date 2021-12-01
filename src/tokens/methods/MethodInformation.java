package tokens.methods;

import tokens.lexeme.Type;

import java.util.ArrayList;
import java.util.List;

public final class MethodInformation {
    public static MethodInformation of(Type type) {
        return new MethodInformation(type, new ArrayList<>());
    }

    private final Type type;
    private final List<Type> argumentTypes;

    private MethodInformation(Type type, List<Type> argumentTypes) {
        this.type = type;
        this.argumentTypes = argumentTypes;
    }

    public Type getType() {
        return type;
    }

    public void addArgumentType(Type type) {
        argumentTypes.add(type);
    }
}
