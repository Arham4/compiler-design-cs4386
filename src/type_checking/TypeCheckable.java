package type_checking;

import tokens.fields.FieldInformation;
import tokens.methods.MethodInformation;

import java.util.Map;

public interface TypeCheckable<R> {
    R typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException;
}
