package type_checking;

public final class TypeCheckException extends Exception {
    public static TypeCheckException withFault(String fault) {
        return new TypeCheckException(fault);
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
