package tokens.methods;

import tokens.fields.FieldInformation;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.args.Args;
import type_checking.TypeCheckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MethodInformation {
    public static MethodInformation of(Type type) {
        return new MethodInformation(type, new ArrayList<>());
    }

    private final Type type;
    private final List<Type> argumentTypes;

    private MethodInformation(Type type, List<Type> argumentTypes) {
        this.type = type;
        this.argumentTypes = argumentTypes;
    }

    public Type getType() {
        return type;
    }

    public void addArgumentType(Type type) {
        argumentTypes.add(type);
    }

    public boolean isArgsNotValid(Args args, int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (args == null) {
            return !argumentTypes.isEmpty();
        }
        int currentIndex = 0;
        Args currentArg = args;
        while (currentArg != null) {
            if (currentIndex == argumentTypes.size()) {
                return true;
            }
            Type currentType = argumentTypes.get(currentIndex);
            Type argType = currentArg.getExpr().typeCheck(scope, fieldSymbolTable, methodSymbolTable);
            if (currentType == Types.BOOLLIT && argType != Types.BOOLLIT && argType != Types.INTLIT) {
                return true;
            } else if (currentType == Types.FLOATLIT && argType != Types.FLOATLIT && argType != Types.INTLIT) {
                return true;
            } else if (currentType != Types.STR && !currentType.equals(argType)) {
                return true;
            }
            currentIndex++;
            currentArg = currentArg.getArgs();
        }
        return false;
    }
}
