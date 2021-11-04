package tokens.methods.args;

import tokens.NonTerminalToken;

public final class PrintLineList implements NonTerminalToken {
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
}
