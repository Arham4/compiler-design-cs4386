package utils;

import information.FieldInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SymbolTableHelper {
    private SymbolTableHelper() {
    }

    public static void removeScopeFromSymbolTable(int scope, Map<String, FieldInformation> symbolTable) {
        List<String> variablesToRemove = new ArrayList<>();
        symbolTable.forEach((variable, fieldInformation) -> {
            fieldInformation.removeScope(scope);
            if (fieldInformation.isEmpty()) {
                variablesToRemove.add(variable);
            }
        });

        for (String variable : variablesToRemove) {
            symbolTable.remove(variable);
        }
    }
}
