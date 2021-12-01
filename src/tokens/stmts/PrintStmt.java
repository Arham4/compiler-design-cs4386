package tokens.stmts;

import tokens.fields.FieldInformation;
import tokens.methods.MethodInformation;
import tokens.methods.args.PrintList;
import type_checking.TypeCheckException;

import java.util.Map;

public final class PrintStmt implements Stmt {
    public static PrintStmt of(PrintList printList) {
        return new PrintStmt(printList);
    }

    private final PrintList printList;

    private PrintStmt(PrintList printList) {
        this.printList = printList;
    }

    @Override
    public String asString(String prefix, int tabs) {
        return prefix + "print(" + printList.asString(tabs) + ");";
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        printList.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        return null;
    }
}
