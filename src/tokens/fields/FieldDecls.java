package tokens.fields;

import tokens.NonTerminalToken;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

public final class FieldDecls implements NonTerminalToken, TypeCheckable<Void> {
    public static final class Builder {
        private FieldDecl fieldDecl = null;
        private FieldDecls fieldDecls = null;

        public Builder fieldDecl(FieldDecl fieldDecl) {
            this.fieldDecl = fieldDecl;
            return this;
        }

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public FieldDecls build() {
            return new FieldDecls(fieldDecl, fieldDecls);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final FieldDecl fieldDecl;
    private final FieldDecls fieldDecls;

    private FieldDecls(FieldDecl fieldDecl, FieldDecls fieldDecls) {
        this.fieldDecl = fieldDecl;
        this.fieldDecls = fieldDecls;
    }

    @Override
    public String asString(int tabs) {
        return (fieldDecls == null ? "" : fieldDecls.asString(tabs)) + StringHelper.withTabs(tabs, fieldDecl.asString(tabs) + "\n");
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (fieldDecls != null) {
            fieldDecls.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        fieldDecl.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
