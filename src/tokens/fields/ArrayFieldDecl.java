package tokens.fields;

import tokens.lexeme.Lexeme;
import type_checking.TypeCheckException;
import utils.StringHelper;

public final class ArrayFieldDecl implements FieldDecl {
    public static final class Builder {
        private Lexeme type;
        private String id;
        private int size;

        public Builder type(Lexeme type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public ArrayFieldDecl build() {
            return new ArrayFieldDecl(type, id, size);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Lexeme type;
    private final String id;
    private final int size;

    private ArrayFieldDecl(Lexeme type, String id, int size) {
        this.type = type;
        this.id = id;
        this.size = size;
    }

    @Override
    public String asString(int tabs) {
        return StringHelper.withTabs(tabs, type.asString(tabs) + " " + id + "[" + size + "];");
    }

    @Override
    public Void typeCheck() throws TypeCheckException {
        return null;
    }
}
