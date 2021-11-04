package tokens.methods;

import tokens.*;
import tokens.fields.FieldDecls;
import tokens.lexeme.OptionalLexeme;
import tokens.methods.args.argdecls.ArgDecls;
import tokens.stmts.Stmts;
import utils.StringHelper;

public final class MethodDecl implements NonTerminalToken {
    public static class Builder {
        private ReturnType returnType;
        private String id;
        private ArgDecls argDecls;
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

        public Builder argDecls(ArgDecls argDecls) {
            this.argDecls = argDecls;
            return this;
        }

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

        public MethodDecl build() {
            return new MethodDecl(returnType, id, argDecls, fieldDecls, stmts, optionalSemi);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final ReturnType returnType;
    private final String id;
    private final ArgDecls argDecls;
    private final FieldDecls fieldDecls;
    private final Stmts stmts;
    private final OptionalLexeme optionalSemi;

    private MethodDecl(ReturnType returnType, String id, ArgDecls argDecls, FieldDecls fieldDecls, Stmts stmts, OptionalLexeme optionalSemi) {
        this.returnType = returnType;
        this.id = id;
        this.argDecls = argDecls;
        this.fieldDecls = fieldDecls;
        this.stmts = stmts;
        this.optionalSemi = optionalSemi;
    }

    @Override
    public String asString(int tabs) {
        return StringHelper.withTabs(tabs, returnType.asString(tabs) + " " + id + "(" + argDecls.asString(tabs) + ") {\n")
                + (fieldDecls == null ? "" : fieldDecls.asString(tabs + 1))
                + (stmts.isShow() && fieldDecls != null ? "\n" : "") + stmts.asString(tabs + 1)
                + StringHelper.withTabs(tabs, "}" + optionalSemi.asString(tabs));
    }
}
