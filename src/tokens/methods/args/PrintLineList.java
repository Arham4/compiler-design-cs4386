package tokens.methods.args;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

public final class PrintLineList implements NonTerminalToken, TypeCheckable<Void> {
    public static PrintLineList withPrintList(PrintList printList) {
        return new PrintLineList(printList);
    }

    public static PrintLineList empty() {
        return new PrintLineList(null);
    }

    private final PrintList printList;

    private PrintLineList(PrintList printList) {
        this.printList = printList;
    }

    @Override
    public String asString(int tabs) {
        if (printList == null) {
            return "";
        }
        return printList.asString(tabs);
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        if (printList != null) {
            printList.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
