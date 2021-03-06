package tokens;

import information.FieldInformation;
import information.MethodInformation;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

public final class Program implements NonTerminalToken, TypeCheckable<Void> {
    public static final class Builder {
        private String id;
        private MemberDecls memberDecls;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder memberDecls(MemberDecls memberDecls) {
            this.memberDecls = memberDecls;
            return this;
        }

        public Program build() {
            return new Program(id, memberDecls);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final String id;
    private final MemberDecls memberDecls;

    private Program(String id, MemberDecls memberDecls) {
        this.id = id;
        this.memberDecls = memberDecls;
    }

    @Override
    public String asString(int tabs) {
        return StringHelper.withTabs(tabs, "class " + id + " {\n" + memberDecls.asString(tabs + 1) + "}");
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        memberDecls.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
