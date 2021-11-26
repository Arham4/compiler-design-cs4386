package utils;

import tokens.lexeme.Type;

import java.util.Map;
import java.util.Optional;

public final class SymbolTableHelper {
    private SymbolTableHelper() {
    }

    public static Optional<Integer> getLowestScope(Map<Integer, Type> scopes) {
        return scopes.keySet().stream().min(Integer::compareTo);
    }

    public static boolean isScopeTooHigh(int scope, Map<Integer, Type> scopes) {
        int lowestScope = getLowestScope(scopes).orElse(Integer.MAX_VALUE);
        return lowestScope > scope;
    }
}
