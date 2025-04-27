package json;

public class Json {

    private Json() {

    }

    public static String stringify(String element) {
        return "\"" + element
                .replaceAll("\\\\([\\\\\"/])", "\\$1")
                .replaceAll("\\\\n", "\\n")
                .replaceAll("\\\\t", "\\t")
                .replaceAll("\\\\x08", "\\t")
                .replaceAll("\\\\x0C", "\\t") + "\"";
    }

    public static String stringify(Number element) {
        return String.valueOf(element);
    }

    public static String stringify(Character element) {
        return Json.stringify(String.valueOf(element));
    }

    public static String stringify(Boolean element) {
        return String.valueOf(element);
    }
}
