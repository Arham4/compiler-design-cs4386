public final class StringHelper {
    private StringHelper() {
    }

    public static String withTabs(int tabs, String text) {
        StringBuilder tabbedStr = new StringBuilder();
        for (int i = 0; i < tabs; i++) {
            tabbedStr.append("\t");
        }
        return tabbedStr + text;
    }

    public static String escapeJava(String text) {
        return text.replace("'", "\\'");
    }
}
