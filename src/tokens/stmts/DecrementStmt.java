package tokens.stmts;

import tokens.id.Name;
import tokens.lexeme.Type;
import type_checking.TypeCheckException;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;
import static utils.SymbolTableHelper.isScopeTooHigh;

public final class DecrementStmt implements Stmt {
    public static DecrementStmt withName(Name name) {
        return new DecrementStmt(name);
    }

    private final Name name;

    private DecrementStmt(Name name) {
        this.name = name;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + name.asString(tabs) + "--;";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (!variableSymbolTable.containsKey(name.getId()) || isScopeTooHigh(scope, variableSymbolTable.get(name.getId()))) {
            throw undeclaredError(name.getId());
        }
        return null;
    }
}
