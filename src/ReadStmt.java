public final class ReadStmt implements Stmt {
    public static ReadStmt of(ReadList readList) {
        return new ReadStmt(readList);
    }

    private final ReadList readList;

    private ReadStmt(ReadList readList) {
        this.readList = readList;
    }

    @Override
    public String asString(int tabs) {
        return "read(" + readList.asString(tabs) + ");";
    }
}
