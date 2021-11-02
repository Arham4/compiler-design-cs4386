public final class ReturnType implements NonTerminalToken {
    public static ReturnType withType(Lexeme type) {
        return new ReturnType(type);
    }

    public static ReturnType withoutType() {
        return new ReturnType(null);
    }

    private final Lexeme type;

    private ReturnType(Lexeme type) {
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
