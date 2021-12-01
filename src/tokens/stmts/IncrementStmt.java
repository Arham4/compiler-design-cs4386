package tokens.stmts;

import tokens.fields.FieldInformation;
import tokens.id.Name;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;
import utils.Pair;

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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Pair<Type, Boolean> varInfo = name.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        Type varType = varInfo.getFirst();
        boolean varIsFinal = varInfo.getSecond();

        if (varIsFinal) {
            throw TypeCheckException.withFault("Error: Final variables cannot be incremented.");
        } else if (varType != Types.INTLIT && varType != Types.FLOATLIT) {
            throw TypeCheckException.withFault("Error: Only ints or floats can be incremented, but " + name.getId() + " is not of those types.");
        }
        return null;
    }
}
