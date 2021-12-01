package tokens.methods.args;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import tokens.id.Name;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.Pair;

import java.util.Map;

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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (fieldSymbolTable.containsKey(name.getId())) {
            Pair<Type, Boolean> info = name.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
            Type type = info.getFirst();
            boolean isFinal = info.getSecond();
            if (type.isArray()) {
                throw TypeCheckException.withFault("Error: Cannot call read stmt on array type");
            } else if (isFinal) {
                throw TypeCheckException.withFault("Error: Cannot call read stmt on final variable");
            }
        } else if (methodSymbolTable.containsKey(name.getId())) {
            throw TypeCheckException.withFault("Error: Cannot call read stmt on a method");
        }
        if (readList != null) {
            readList.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
