package tokens.methods.args.argdecls;

import tokens.lexeme.Type;
import type_checking.TypeCheckException;

import java.util.HashMap;
import java.util.Map;

import static type_checking.TypeCheckException.redeclarationError;

public final class RegularArgDecl implements ArgDecl {
    public static class Builder {
        private Type type;
        private String id;

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public RegularArgDecl build() {
            return new RegularArgDecl(type, id);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Type type;
    private final String id;

    public RegularArgDecl(Type type, String id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public String asString(int tabs) {
        return type.asString(tabs) + " " + id;
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (!variableSymbolTable.containsKey(id)) {
            variableSymbolTable.put(id, new HashMap<>());
        }
        if (variableSymbolTable.get(id).containsKey(scope)) {
            throw redeclarationError(id, scope);
        }
        variableSymbolTable.get(id).put(scope, type);
        return null;
    }
}
