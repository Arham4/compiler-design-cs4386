package information;

import tokens.lexeme.Type;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class FieldInformation {
    public static FieldInformation empty() {
        return new FieldInformation(new HashMap<>(), new HashMap<>());
    }

    private final Map<Integer, Boolean> finals;
    private final Map<Integer, Type> scopes;

    private FieldInformation(Map<Integer, Boolean> finals, Map<Integer, Type> scopes) {
        this.finals = finals;
        this.scopes = scopes;
    }

    public boolean isFinalAt(int scope) {
        return finals.get(scope);
    }

    public Type getTypeAt(int scope) {
        return scopes.get(scope);
    }

    public void put(int scope, Type type, boolean isFinal) {
        finals.put(scope, isFinal);
        scopes.put(scope, type);
    }

    public Type getClosestScopeType(int scope) {
        return getClosestScopeInfo(scope).getFirst();
    }

    public boolean isClosestScopeFinal(int scope) {
        return getClosestScopeInfo(scope).getSecond();
    }

    public Pair<Type, Boolean> getClosestScopeInfo(int scope) {
        int highestScope = Integer.min(scope, getHighestScope(scopes).orElse(scope));
        if (scopes.containsKey(highestScope)) {
            return Pair.of(scopes.get(highestScope), finals.get(highestScope));
        }
        return null;
    }

    public boolean isScopeTooHigh(int scope) {
        int lowestScope = getLowestScope(scopes).orElse(Integer.MAX_VALUE);
        return lowestScope > scope;
    }

    public boolean isAlreadyDeclaredAtScope(int scope) {
        return scopes.containsKey(scope);
    }

    public void removeScope(int scope) {
        finals.remove(scope);
        scopes.remove(scope);
    }

    public boolean isEmpty() {
        return scopes.isEmpty();
    }

    private static Optional<Integer> getLowestScope(Map<Integer, Type> scopes) {
        return scopes.keySet().stream().min(Integer::compareTo);
    }

    private static Optional<Integer> getHighestScope(Map<Integer, Type> scopes) {
        return scopes.keySet().stream().max(Integer::compareTo);
    }
}
