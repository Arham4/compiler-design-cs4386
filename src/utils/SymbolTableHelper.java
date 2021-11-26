package utils;

import tokens.lexeme.Type;

import java.util.Map;
import java.util.Optional;

public final class SymbolTableHelper {
    private SymbolTableHelper() {
    }

    private static Optional<Integer> getLowestScope(Map<Integer, Type> scopes) {
        return scopes.keySet().stream().min(Integer::compareTo);
    }

    private static Optional<Integer> getHighestScope(Map<Integer, Type> scopes) {
        return scopes.keySet().stream().max(Integer::compareTo);
    }

    public static boolean isScopeTooHigh(int scope, Map<Integer, Type> scopes) {
        int lowestScope = getLowestScope(scopes).orElse(Integer.MAX_VALUE);
        return lowestScope > scope;
    }

    public static Type getClosestScopeType(int scope, Map<Integer, Type> scopes) {
        int highestScope = Integer.min(scope, getHighestScope(scopes).orElse(scope));
        if (scopes.containsKey(highestScope)) {
            return scopes.get(highestScope);
        }
        return null;
    }
}
