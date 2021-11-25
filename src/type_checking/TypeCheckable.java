package type_checking;

import java.util.Map;

public interface TypeCheckable<R> {
    R typeCheck(Map<String, String> symbolTable) throws TypeCheckException;
}
