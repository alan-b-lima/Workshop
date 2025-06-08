package visual.tui.shell;

import java.util.Scanner;
import java.util.regex.Pattern;

import visual.tui.shell.exit.ExitCode;
import visual.tui.shell.exit.ExitMessage;

/**
 * Uma interface de linha de comando simples para executar comandos.
 * 
 * @author Alan Lima
 */
public class Shell {

    /**
     * Contexto atual do shell, usado para exibir o prompt.
     */
    private String context;

    /**
     * Indica se o shell está em execução.
     */
    private boolean running;

    /**
     * Construtor padrão do Shell, inicializa o contexto como uma string vazia.
     */
    public Shell() {
        this.context = "";
        this.running = true;
    }

    /**
     * Padrão regex para remover espaços em branco à esquerda de uma string.
     */
    private static final Pattern REMOVE_LEADING_WHITESPACE = Pattern.compile("\\s*(.*)");

    /**
     * Padrão regex para dividir uma string em palavras, usando espaços em branco
     * como delimitadores.
     */
    private static final Pattern SPLIT_PROMPT_PATTERN = Pattern.compile("\\s+");

    /**
     * Inicia o shell, permitindo que o usuário insira comandos.
     */
    public void launch() {
        Scanner scanner = new Scanner(System.in);

        while (this.running) {
            System.out.printf("%s> ", this.context);
            String rawPrompt = scanner.nextLine();

            String[] prompt = decodePrompt(rawPrompt);

            if (prompt.length == 0) {
                continue;
            }

            if (prompt[0].equals("\\q")) {
                this.running = false;
                continue;
            }

            runCommand(prompt);
        }

        scanner.close();
    }

    /**
     * Executa um comando com os argumentos fornecidos.
     * 
     * @param args argumentos do comando a ser executado.
     */
    public void runCommand(String... args) {
        Command command = Commands.getCommand(context, args[0]);
        ExitMessage msg = command.execute(args);
        applyExitMessage(msg);
    }

    /**
     * Aplica uma mensagem de saída ao shell, alterando o contexto ou encerrando-o.
     * 
     * @param msg a mensagem de saída a ser aplicada.
     */
    public void applyExitMessage(ExitMessage msg) {

        switch (msg.code()) {

            case ExitCode.EXIT_CONTEXT:
                if (this.context.isEmpty()) {
                    this.running = false;
                    break;
                }

                int lastContextIndex = context.lastIndexOf('.');
                if (lastContextIndex == -1) {
                    this.context = "";
                    break;
                }

                this.context = context.substring(0, lastContextIndex);
                break;

            case ExitCode.ENTER_CONTEXT:
                if (this.context.isEmpty()) {
                    context = msg.context();
                    break;
                }

                context = String.format("%s.%s", this.context, msg.context());
                break;

            case ExitCode.EXIT_SHELL:
                running = false;
                break;

            default:
                break;
        }
    }

    /**
     * Decodifica o prompt removendo espaços em branco à esquerda e dividindo-o em
     * palavras.
     * 
     * @param prompt a string do prompt a ser decodificada.
     * @return um array de strings contendo as palavras do prompt.
     */
    private static String[] decodePrompt(String prompt) {
        String trimmedPrompt = REMOVE_LEADING_WHITESPACE.matcher(prompt).replaceAll("$1");
        return SPLIT_PROMPT_PATTERN.split(trimmedPrompt);
    }
}