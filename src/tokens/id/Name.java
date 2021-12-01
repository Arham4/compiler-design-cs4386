package tokens.id;

import tokens.NonTerminalToken;
import tokens.expr.Expr;
import tokens.fields.FieldInformation;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import tokens.methods.MethodInformation;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;
import utils.Pair;

import java.util.Map;

import static type_checking.TypeCheckException.undeclaredError;

public interface Name extends NonTerminalToken, TypeCheckable<Pair<Type, Boolean>> {
    static Name simple(String id) {
        return new Name() {
            @Override
            public String asString(int tabs) {
                return id;
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public Pair<Type, Boolean> typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                if (!fieldSymbolTable.containsKey(id) || fieldSymbolTable.get(id).isScopeTooHigh(scope)) {
                    throw undeclaredError(id);
                }

                return fieldSymbolTable.get(id).getClosestScopeInfo(scope);
            }
        };
    }

    static Name array(String id, Expr expr) {
        return new Name() {
            @Override
            public String asString(int tabs) {
                return id + "[" + expr.asString(tabs) + "]";
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public Pair<Type, Boolean> typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
                if (!fieldSymbolTable.containsKey(id) || fieldSymbolTable.get(id).isScopeTooHigh(scope)) {
                    throw undeclaredError(id);
                }

                Type type = fieldSymbolTable.get(id).getClosestScopeType(scope);
                if (type.getType().equals(Types.BOOLLIT.getType())) {
                    type = Types.BOOLLIT;
                } else if (type.getType().equals(Types.FLOATLIT.getType())) {
                    type = Types.FLOATLIT;
                } else if (type.getType().equals(Types.CHARLIT.getType())) {
                    type = Types.CHARLIT;
                } else if (type.getType().equals(Types.INTLIT.getType())) {
                    type = Types.INTLIT;
                } else if (type.getType().equals(Types.STR.getType())) {
                    type = Types.STR;
                }
                return Pair.of(type, fieldSymbolTable.get(id).isClosestScopeFinal(scope));
            }
        };
    }

    String getId();
}
