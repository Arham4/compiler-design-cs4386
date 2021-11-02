public final class PrintLineStmt implements Stmt {
    public static PrintLineStmt of(PrintLineList printLineList) {
        return new PrintLineStmt(printLineList);
    }

    private final PrintLineList printLineList;

    private PrintLineStmt(PrintLineList printLineList) {
        this.printLineList = printLineList;
    }

    @Override
    public String asString(int tabs) {
        return "printline(" + printLineList.asString(tabs) + ");";
    }
}
