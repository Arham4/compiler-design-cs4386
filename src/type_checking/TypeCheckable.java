package type_checking;

import tokens.fields.FieldInformation;
import tokens.lexeme.Type;

import java.util.Map;

public interface TypeCheckable<R> {
    R typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException;
}
