package type_checking;

import tokens.lexeme.Type;

import java.util.Map;

public interface TypeCheckable<R> {
    R typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException;
}
