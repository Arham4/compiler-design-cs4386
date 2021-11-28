package tokens.id;

import tokens.NonTerminalToken;
import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

import static utils.SymbolTableHelper.getClosestScopeType;

public interface Name extends NonTerminalToken, TypeCheckable<Type> {
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
            public Type typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
                return getClosestScopeType(scope, variableSymbolTable.get(id));
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
            public Type typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
                Type type = getClosestScopeType(scope, variableSymbolTable.get(id));
                if (type.getType().equals(Types.BOOLLIT.getType())) {
                    return Types.BOOLLIT;
                } else if (type.getType().equals(Types.FLOATLIT.getType())) {
                    return Types.FLOATLIT;
                } else if (type.getType().equals(Types.CHARLIT.getType())) {
                    return Types.CHARLIT;
                } else if (type.getType().equals(Types.INTLIT.getType())) {
                    return Types.INTLIT;
                } else if (type.getType().equals(Types.STR.getType())) {
                    return Types.STR;
                }
                return Type.of(type.getType());
            }
        };
    }

    String getId();
}
