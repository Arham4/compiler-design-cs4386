public interface Name extends NonTerminalToken {
    static Name simple(String id) {
        return tabs -> id;
    }

    static Name array(String id, Expr expr) {
        return tabs -> id + "[" + expr.asString(tabs) + "]";
    }
}
