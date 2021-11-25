package tokens.fields;

import tokens.lexeme.Type;
import type_checking.TypeCheckException;
import utils.StringHelper;

import java.util.Map;

public final class ArrayFieldDecl implements FieldDecl {
    public static final class Builder {
        private Type type;
        private String id;
        private int size;

        public Builder type(Type type) {
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

    private final Type type;
    private final String id;
    private final int size;

    private ArrayFieldDecl(Type type, String id, int size) {
        this.type = type;
        this.id = id;
        this.size = size;
    }

    @Override
    public String asString(int tabs) {
        return StringHelper.withTabs(tabs, type.asString(tabs) + " " + id + "[" + size + "];");
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        return null;
    }
}
