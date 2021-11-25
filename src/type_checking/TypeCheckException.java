package type_checking;

import tokens.lexeme.Type;

public final class TypeCheckException extends Exception {
    public static TypeCheckException withFault(String fault) {
        return new TypeCheckException(fault);
    }

    public static TypeCheckException conversionError(Type type, String original) {
        return TypeCheckException.withFault("Error: cannot convert " + type.getType() + " to " + original);
    }

    public static TypeCheckException redeclarationError(String id, int scope) {
        return TypeCheckException.withFault("Error: " + id + " cannot be redeclared on scope " + scope);
    }

    private final String fault;

    public TypeCheckException(String fault) {
        this.fault = fault;
    }

    @Override
    public String toString() {
        return fault;
    }
}
