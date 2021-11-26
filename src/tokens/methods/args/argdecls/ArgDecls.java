package tokens.methods.args.argdecls;

import tokens.NonTerminalToken;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

public final class ArgDecls implements NonTerminalToken, TypeCheckable<Void> {
    public static ArgDecls of(ArgDeclList argDeclList) {
        return new ArgDecls(argDeclList);
    }

    public static ArgDecls empty() {
        return new ArgDecls(null);
    }

    private final ArgDeclList argDeclList;

    public ArgDecls(ArgDeclList argDeclList) {
        this.argDeclList = argDeclList;
    }

    @Override
    public String asString(int tabs) {
        if (argDeclList == null) {
            return "";
        }
        return argDeclList.asString(tabs);
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        return null;
    }
}
