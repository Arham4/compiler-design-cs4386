package tokens.methods.args;

import tokens.NonTerminalToken;
import tokens.lexeme.Type;
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
    public Void typeCheck(int scope, Map<String, Map<Integer, Type>> variableSymbolTable, Map<String, Type> methodSymbolTable) throws TypeCheckException {
        if (printList != null) {
            printList.typeCheck(scope, variableSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
