package tokens.lexeme;

public final class Types {
    private Types() {
    }

    public static Type INTLIT = Type.of("int");
    public static Type CHARLIT = Type.of("char");
    public static Type BOOLLIT = Type.of("bool");
    public static Type FLOATLIT = Type.of("float");
}
