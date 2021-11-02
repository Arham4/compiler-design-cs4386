public final class PrintStmt implements Stmt {
    public static PrintStmt of(PrintList printList) {
        return new PrintStmt(printList);
    }

    private final PrintList printList;

    private PrintStmt(PrintList printList) {
        this.printList = printList;
    }

    @Override
    public String asString(int tabs) {
        return "print(" + printList.asString(tabs) + ");";
    }
}
