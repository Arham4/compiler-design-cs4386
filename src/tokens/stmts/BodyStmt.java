package tokens.stmts;

import information.FieldInformation;
import information.MethodInformation;
import tokens.fields.FieldDecls;
import tokens.lexeme.OptionalLexeme;
import type_checking.TypeCheckException;
import utils.StringHelper;

import java.util.Map;

public final class BodyStmt implements Stmt, Nestable {
    public static class Builder {
        private FieldDecls fieldDecls;
        private Stmts stmts;
        private OptionalLexeme optionalSemi;

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public Builder stmts(Stmts stmts) {
            this.stmts = stmts;
            return this;
        }

        public Builder optionalSemi(OptionalLexeme optionalSemi) {
            this.optionalSemi = optionalSemi;
            return this;
        }

        public BodyStmt build() {
            return new BodyStmt(fieldDecls, stmts, optionalSemi);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String methodId;
    private final FieldDecls fieldDecls;
    private final Stmts stmts;
    private final OptionalLexeme optionalSemi;

    private BodyStmt(FieldDecls fieldDecls, Stmts stmts, OptionalLexeme optionalSemi) {
        this.fieldDecls = fieldDecls;
        this.stmts = stmts;
        this.optionalSemi = optionalSemi;
    }

    @Override
    public boolean hasReturnStmt() {
        return stmts.hasReturnStmt();
    }

    @Override
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "{\n"
                + (fieldDecls == null ? "" : fieldDecls.asString(tabs + 1))
                + (stmts == null ? "" : stmts.asString(tabs + 1))
                + StringHelper.withTabs(tabs, "}" + optionalSemi.asString(tabs));
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (fieldDecls != null) {
            fieldDecls.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        if (stmts != null) {
            stmts.setMethodId(methodId);
            stmts.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
