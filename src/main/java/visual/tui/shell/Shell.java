package visual.tui.shell;

import java.util.Scanner;
import java.util.regex.Pattern;

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
     * Construtor padrão do Shell, inicializa o contexto como uma string vazia.
     */
    public Shell() {
        context = "";
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

    /**
     * Executa um comando com os argumentos fornecidos.
     * 
     * @param args argumentos do comando a ser executado.
     */
    public static void runCommand(String... args) {
        Command command = Commands.getCommand(args[0]);
        command.execute(args);
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