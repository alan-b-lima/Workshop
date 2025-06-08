package visual.tui.shell;

import visual.tui.shell.commands.*;
import visual.tui.shell.entry.CommandEntry;
import visual.tui.shell.entry.ContextEntry;
import visual.tui.shell.entry.Entry;

/**
 * Classe que gerencia os comandos e contextos disponíveis no sistema.
 */
public final class Commands {

    private static final ContextEntry COMMANDS = new ContextEntry(new Entry[] {
            new CommandEntry("ajuda", new Help()),
            new ContextEntry("estoque", new Entry[] {

            }),
    });

    /**
     * Construtor privado.
     */
    private Commands() {

    }

    public static Command getCommand(String command) {
        for (int i = 0; i < COMMANDS.length(); i++) {
            // TODO
        }

        return EMPTY_COMMAND;
    }

    public static final Command EMPTY_COMMAND = (String[] _) -> {
        System.out.println("eu sou o commando vazio, lembre de me apagar depois");
    };
}
