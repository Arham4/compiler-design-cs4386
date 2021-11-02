public final class PrintList implements NonTerminalToken {
    public static class Builder {
        private Expr expr;
        private PrintList printList = null;

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public Builder printList(PrintList printList) {
            this.printList = printList;
            return this;
        }

        public PrintList build() {
            return new PrintList(expr, printList);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Expr expr;
    private final PrintList printList;

    private PrintList(Expr expr, PrintList printList) {
        this.expr = expr;
        this.printList = printList;
    }

    @Override
    public String asString(int tabs) {
        return expr.asString(tabs) + (printList == null ? "" : ", " + printList.asString(tabs));
    }
}
