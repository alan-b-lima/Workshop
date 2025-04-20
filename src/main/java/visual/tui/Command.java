package visual.tui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

    private static final Pattern PROMPT_PATTERN = Pattern.compile("\"((?:[^\"\\\\]|\\\\[\"nt])+)\"|([^\\t\\n\\r ]+)");

    public static String[] decodePrompt(String prompt) {
        if (prompt == null || prompt.matches("\\s*")) {
            return new String[0];
        }

        Matcher matcher = PROMPT_PATTERN.matcher(prompt);
        ArrayList<String> args = new ArrayList<>();

        while (matcher.find()) {
            String match;

            if ((match = matcher.group(1)) != null) {
                args.add(match
                        .replaceAll("\\\\n", "\n")
                        .replaceAll("\\\\t", "\t")
                        .replaceAll("\\\\([\\s\\S])", "$1"));

                continue;
            }

            if ((match = matcher.group(2)) != null) {
                args.add(match);

                continue;
            }
        }

        return args.toArray(new String[0]);
    }
}