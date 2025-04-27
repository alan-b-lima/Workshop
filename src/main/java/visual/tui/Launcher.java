package visual.tui;

import java.io.File;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.matches("win(downs)?.*")) {

            String java = String.format("\"%s%sbin%sjava.exe\" -cp \"%s%starget%sclasses\" visual.tui.Main",
                    System.getProperty("java.home"), File.separator, File.separator,
                    System.getProperty("user.dir"), File.separator, File.separator);

            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "chcp 65001 > nul && " + java);

            try {
                pb.inheritIO().start().waitFor();
            } catch (IOException | InterruptedException err) {
                System.out.println("Failed to start");
            }
        }

        else {
            Main.main(args);
        }
    }
}