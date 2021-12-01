package type_checking;

import information.FieldInformation;
import information.MethodInformation;

import java.util.Map;

public interface TypeCheckable<R> {
    R typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException;
}
