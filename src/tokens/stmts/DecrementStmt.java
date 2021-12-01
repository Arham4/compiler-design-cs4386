package tokens.stmts;

import tokens.fields.FieldInformation;
import tokens.id.Name;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;

import java.util.Map;

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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        // todo check for final
        Type varType = name.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        if (varType != Types.INTLIT && varType != Types.FLOATLIT) {
            throw TypeCheckException.withFault("Error: Only ints or floats can be decremented, but " + name.getId() + " is not of those types.");
        }
        return null;
    }
}
