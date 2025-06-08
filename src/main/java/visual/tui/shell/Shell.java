package visual.tui.shell;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Shell {
    
    private String context;

    public Shell() {
        context = "";
    }

    private static final Pattern REMOVE_LEADING_WHITESPACE = Pattern.compile("\\s*(.*)");

    private static final Pattern SPLIT_PROMPT_PATTERN = Pattern.compile("\\s+");

    public void launch() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("%s> ", context);
            String rawPrompt = scanner.nextLine();

            String[] prompt = decodePrompt(rawPrompt);

            if (prompt.length == 0) {
                continue;
            }

            if (prompt[0].equals("exit")) {
                break;
            }

            runCommand(prompt);
        }

        scanner.close();
    }

    public static void runCommand(String... args) {
        Command command = Commands.getCommand(args[0]);
        command.execute(args);
    }

    private static String[] decodePrompt(String prompt) {
        String trimmedPrompt = REMOVE_LEADING_WHITESPACE.matcher(prompt).replaceAll("$1");
        return SPLIT_PROMPT_PATTERN.split(trimmedPrompt);
    }
}