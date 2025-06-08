package visual.tui.shell;

import visual.tui.shell.exit.ExitMessage;

/**
 * Interface funcional para definir comandos que podem ser executados
 * na interface de usuário baseada em texto (TUI).
 * 
 * @author Alan Lima
 */
@FunctionalInterface
public interface Command {

    /**
     * Método para executar o comando com os argumentos fornecidos.
     *
     * @param args argumentos passados para o comando.
     * 
     * @return uma mensagem de saída após a execução do comando.
     */
    ExitMessage execute(String[] args);
}