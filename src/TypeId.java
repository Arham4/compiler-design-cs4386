public final class TypeId {
    public static TypeId of(Lexeme type, String id) {
        return new TypeId(type, id);
    }

    private final Lexeme type;
    private final String id;

    private TypeId(Lexeme type, String id) {
        this.type = type;
        this.id = id;
    }

    public Lexeme getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
