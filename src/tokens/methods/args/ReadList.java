package tokens.methods.args;

import tokens.NonTerminalToken;
import tokens.id.Name;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;

public final class ReadList implements NonTerminalToken, TypeCheckable<Void> {
    public static class Builder {
        private Name name;
        private ReadList readList = null;

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder readList(ReadList readList) {
            this.readList = readList;
            return this;
        }

        public ReadList build() {
            return new ReadList(name, readList);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Name name;
    private final ReadList readList;

    ReadList(Name name, ReadList readList) {
        this.name = name;
        this.readList = readList;
    }

    @Override
    public String asString(int tabs) {
        return name.asString(tabs) + (readList == null ? "" : ", " + readList.asString(tabs));
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        // todo implement not working on final
        if (!variableSymbolTable.containsKey(name.getId()) && !methodSymbolTable.containsKey(name.getId())) {
            throw undeclaredError(name.getId());
        }
        if (variableSymbolTable.containsKey(name.getId())) {
            Type type = name.typeCheck(scope, variableSymbolTable, methodSymbolTable);
            if (type.isArray()) {
                throw TypeCheckException.withFault("Cannot call read stmt on array type");
            }
        } else if (methodSymbolTable.containsKey(name.getId()) && methodSymbolTable.get(name.getId()) == Types.VOID) {
            throw TypeCheckException.withFault("Cannot call read stmt on void type");
        }
        if (readList != null) {
            readList.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
