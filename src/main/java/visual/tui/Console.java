package visual.tui;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Console {

    private Console() {

    }

    // private static final Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
    // private static final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    // private static final PrintStream err = new PrintStream(System.err, true, StandardCharsets.UTF_8);

    private static final PrintWriter out = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

    public static void printf(String format, Object... args) {
        Console.out.write("Olá, 世界👋");
    }
}
