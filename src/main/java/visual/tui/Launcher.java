package visual.tui;

import java.io.File;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {

        if (System.getProperty("os.name").matches("^[Ww]in(down)?.*$")) {

            String java = String.format("\"%s%sbin%sjava.exe\" -cp \"%s%starget%sclasses\" visual.tui.Main",
                    System.getProperty("java.home"), File.separator, File.separator,
                    System.getProperty("user.dir"), File.separator, File.separator);

            try {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "chcp 65001 > nul && " + java);
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