public final class StringHelper {
    private StringHelper() {
    }

    public static String tabs(int tabs) {
        StringBuilder tabbedStr = new StringBuilder();
        for (int i = 0; i < tabs; i++) {
            tabbedStr.append("    ");
        }
        return tabbedStr.toString();
    }

    public static String withTabs(int tabs, String text) {
        return tabs(tabs) + text;
    }

    public static String escapeJava(String text) {
        return text.replace("'", "\\'");
    }
}
