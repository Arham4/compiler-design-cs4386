package tokens.methods;

import tokens.NonTerminalToken;
import tokens.fields.FieldDecls;
import tokens.fields.FieldInformation;
import tokens.lexeme.OptionalLexeme;
import tokens.lexeme.Types;
import tokens.methods.args.argdecls.ArgDeclList;
import tokens.stmts.Stmts;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.StringHelper;

import java.util.Map;

import static type_checking.TypeCheckException.redeclarationError;

public final class MethodDecl implements NonTerminalToken, TypeCheckable<Void> {
    public static class Builder {
        private ReturnType returnType;
        private String id;
        private ArgDeclList argDeclList;
        private FieldDecls fieldDecls;
        private Stmts stmts;
        private OptionalLexeme optionalSemi;

        public Builder returnType(ReturnType returnType) {
            this.returnType = returnType;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder argDeclList(ArgDeclList argDeclList) throws Exception {
            if (id == null) {
                throw new Exception("ID must be set before setting an arg decl list");
            }
            this.argDeclList = argDeclList;
            argDeclList.setMethodId(id);
            return this;
        }

        public Builder fieldDecls(FieldDecls fieldDecls) {
            this.fieldDecls = fieldDecls;
            return this;
        }

        public Builder stmts(Stmts stmts) throws Exception {
            if (id == null) {
                throw new Exception("ID must be set before setting an arg decl list");
            }
            this.stmts = stmts;
            stmts.setMethodId(id);
            return this;
        }

        public Builder optionalSemi(OptionalLexeme optionalSemi) {
            this.optionalSemi = optionalSemi;
            return this;
        }

        public MethodDecl build() {
            return new MethodDecl(returnType, id, argDeclList, fieldDecls, stmts, optionalSemi);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final ReturnType returnType;
    private final String id;
    private final ArgDeclList argDeclList;
    private final FieldDecls fieldDecls;
    private final Stmts stmts;
    private final OptionalLexeme optionalSemi;

    private MethodDecl(ReturnType returnType, String id, ArgDeclList argDeclList, FieldDecls fieldDecls, Stmts stmts, OptionalLexeme optionalSemi) {
        this.returnType = returnType;
        this.id = id;
        this.argDeclList = argDeclList;
        this.fieldDecls = fieldDecls;
        this.stmts = stmts;
        this.optionalSemi = optionalSemi;
    }

    @Override
    public String asString(int tabs) {
        return StringHelper.withTabs(tabs, returnType.asString(tabs) + " " + id + "(" + (argDeclList == null ? "" : argDeclList.asString(tabs)) + ") {\n")
                + (fieldDecls == null ? "" : fieldDecls.asString(tabs + 1))
                + (stmts != null && fieldDecls != null ? "\n" : "") + (stmts != null ? stmts.asString(tabs + 1) : "")
                + StringHelper.withTabs(tabs, "}" + optionalSemi.asString(tabs));
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (methodSymbolTable.containsKey(id)) {
            throw redeclarationError(id, scope);
        }
        methodSymbolTable.put(id, MethodInformation.of(returnType.getType() == null ? Types.VOID : returnType.getType()));

        if (argDeclList != null) {
            argDeclList.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        if (fieldDecls != null) {
            fieldDecls.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        if (stmts != null) {
            stmts.typeCheck(scope + 1, fieldSymbolTable, methodSymbolTable);
        }
        if (returnType.getType() != null && stmts != null && !stmts.hasReturnStmt()) {
            throw TypeCheckException.withFault("Error: No return stmt in method " + id + " with expected return type " + returnType.getType().getType());
        }
        return null;
    }
}
