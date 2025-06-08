package visual.tui.shell;

import visual.tui.shell.commands.Empty;
import visual.tui.shell.commands.Exit;
import visual.tui.shell.entry.CommandEntry;
import visual.tui.shell.entry.ContextEntry;
import visual.tui.shell.entry.Entry;
import visual.tui.shell.exit.ExitMessage;

/**
 * Classe que gerencia os comandos e contextos disponíveis no sistema.
 * 
 * @author Alan Lima
 */
public final class Commands {

    /**
     * Construtor privado.
     */
    private Commands() {

    }
    
    /**
     * Lista de comandos disponíveis no sistema.
     */
    private static final ContextEntry COMMANDS = new ContextEntry(new Entry[] {
            new CommandEntry("sair", new Exit()),
            new ContextEntry("estoque", new Entry[] {
                    new CommandEntry("sair", new Exit()),
            }),
    });

    /**
     * Obtém um comando com base no nome fornecido.
     * 
     * @param context o contexto em que o comando é buscado.
     * @param command o nome do comando a ser buscado.
     * @return o comando correspondente ou um comando vazio se não for encontrado.
     */
    public static Command getCommand(String context, String command) {
        if (command == null || command.isEmpty()) {
            return new Empty();
        }

        if (command.contains(".")) {
            return (String[] _) -> {
                System.out.println("referência relativa não implementada");
                return ExitMessage.DEFAULT;
            };
        }

        ContextEntry current = COMMANDS;

        if (!context.isEmpty()) {
            String[] subContexts = context.split("\\.");

            sub: for (String subContext : subContexts) {
                for (Entry entry : current.entries()) {

                    if (!entry.context().equals(subContext)) {
                        continue;
                    }

                    if (entry instanceof CommandEntry) {
                        return new Empty();
                    }

                    if (entry instanceof ContextEntry contextEntry) {
                        current = contextEntry;
                        continue sub;
                    }
                }

                return new Empty();
            }
        }

        for (Entry entry : current.entries()) {
            if (entry.context().equals(command)) {
                return entry.command();
            }
        }

        return new Empty();
    }
}