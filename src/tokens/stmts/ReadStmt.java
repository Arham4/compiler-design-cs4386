package tokens.stmts;

import tokens.fields.FieldInformation;
import tokens.methods.MethodInformation;
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
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        readList.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
