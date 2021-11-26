package tokens.stmts;

import tokens.lexeme.Type;
import tokens.methods.args.ReadList;
import type_checking.TypeCheckException;

import java.util.Map;

public final class ReadStmt implements Stmt {
    public static ReadStmt of(ReadList readList) {
        return new ReadStmt(readList);
    }

    private final ReadList readList;

    private ReadStmt(ReadList readList) {
        this.readList = readList;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "read(" + readList.asString(tabs) + ");";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        readList.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        return null;
    }
}
