package type_checking;

import tokens.lexeme.Type;

public final class TypeCheckException extends Exception {
    public static TypeCheckException withFault(String fault) {
        return new TypeCheckException(fault);
    }

    public static TypeCheckException conversionError(Type type, String original) {
        return TypeCheckException.withFault("Error: cannot convert " + type.getType() + " to " + original);
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
