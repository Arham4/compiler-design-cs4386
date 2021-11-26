package tokens.stmts;

import tokens.lexeme.Type;
import tokens.methods.args.PrintLineList;
import type_checking.TypeCheckException;

import java.util.Map;

public final class PrintLineStmt implements Stmt {
    public static PrintLineStmt of(PrintLineList printLineList) {
        return new PrintLineStmt(printLineList);
    }

    private final PrintLineList printLineList;

    private PrintLineStmt(PrintLineList printLineList) {
        this.printLineList = printLineList;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "printline(" + printLineList.asString(tabs) + ");";
    }

    @Override
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        printLineList.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        return null;
    }
}
