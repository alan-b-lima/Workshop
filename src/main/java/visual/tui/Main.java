package visual.tui;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");
        String input = scanner.nextLine();

        System.err.println(input);
        for (String arg : Command.decodePrompt(input)) {
            System.out.print(arg + " ");
        }

        System.out.println();

        scanner.close();
    }
}
