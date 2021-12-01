package tokens.stmts;

import tokens.fields.FieldInformation;
import tokens.lexeme.Type;
import tokens.methods.args.Args;
import type_checking.TypeCheckException;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;

public final class MethodInvocationStmt implements Stmt {
    public static MethodInvocationStmt blank(String id) {
        return new MethodInvocationStmt(id, null);
    }

    public static MethodInvocationStmt withArgs(String id, Args args) {
        return new MethodInvocationStmt(id, args);
    }

    private final String id;
    private final Args args;

    private MethodInvocationStmt(String id, Args args) {
        this.id = id;
        this.args = args;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + id + "(" + (args == null ? "" : args.asString(tabs)) + ");";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (!methodSymbolTable.containsKey(id)) {
            throw undeclaredError(id);
        }
        // todo check with args
        return null;
    }
}
