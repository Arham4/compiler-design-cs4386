package tokens.fields;

import tokens.lexeme.Type;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;

import java.util.Map;

import static type_checking.TypeCheckException.redeclarationError;

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
        return type.asString(tabs) + " " + id + "[" + size + "];";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (!fieldSymbolTable.containsKey(id)) {
            fieldSymbolTable.put(id, FieldInformation.empty());
        }
        if (fieldSymbolTable.get(id).isAlreadyDeclaredAtScope(scope)) {
            throw redeclarationError(id, scope);
        }
        fieldSymbolTable.get(id).put(scope, type.asArray(), false);
        return null;
    }
}
