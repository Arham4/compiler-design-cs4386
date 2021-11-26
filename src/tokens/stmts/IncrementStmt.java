package tokens.stmts;

import tokens.id.Name;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;

import java.util.Map;

public final class IncrementStmt implements Stmt {
    public static IncrementStmt withName(Name name) {
        return new IncrementStmt(name);
    }

    private final Name name;

    private IncrementStmt(Name name) {
        this.name = name;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + "++;";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        return null;
    }
}
