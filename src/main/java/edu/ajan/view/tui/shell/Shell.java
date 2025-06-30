package edu.ajan.view.tui.shell;

import java.util.Arrays;
import java.util.regex.Pattern;

import edu.ajan.view.Console;
import edu.ajan.view.tui.shell.command.*;
import edu.ajan.view.tui.shell.entry.Context;
import edu.ajan.view.tui.shell.entry.Entry;
import edu.ajan.view.tui.shell.exit.ExitCode;
import edu.ajan.view.tui.shell.exit.ExitMessage;

/**
 * Uma interface de linha de comando simples para executar comandos.
 * 
 * @author Alan Lima
 */
public class Shell {

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
     * Formato do prompt de entrada do REPL (Read-Eval-Print Loop), que exibe o
     * contexto atual e o nome do usuário (não implementado).
     */
    private static final String REPL_INPUT_PROMPT_FORMAT = "\u250C\u2500\u2500[%s][%s]\n\u2514\u2500$ ";

    /**
     * Contexto raiz do shell, contendo exemplos e comandos organizados em
     * subcontextos.
     */
    private static final Context ROOT = new Context(new Entry[] {
            new Now()
    });

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
        this.context = Entry.ROOT_CONTEXT;
        this.running = true;
    }

    /**
     * Inicia o shell, permitindo que o usuário insira comandos.
     */
    public void launch() {

        while (this.running) {

            String REPLInputPrompt = String.format(REPL_INPUT_PROMPT_FORMAT, this.context, "");
            String rawPrompt = Console.in.readLine(REPLInputPrompt);

            String[] prompt = decodePrompt(rawPrompt);

            if (prompt.length == 0) {
                continue;
            }

            if (prompt[0].equals("\\q")) {
                this.running = false;
                continue;
            }

            ExitMessage msg = executeEntry(prompt[0], prompt);
            applyMessage(msg);
        }
    }

    /**
     * Executa uma entrada no contexto atual do shell com base no nome fornecido e
     * nos argumentos fornecidos.
     * 
     * @param name o nome da entrada a ser executada, que pode ser um comando ou um
     *             contexto.
     * @param args os argumentos a serem passados para a entrada executada.
     * @return uma mensagem de saída contendo o código de saída e uma mensagem
     *         associada, ou null se a entrada não for encontrada.
     */
    public ExitMessage executeEntry(String name, String[] args) {
        if (name == null) {
            return new ExitMessage(ExitCode.INVALID_ARGUMENT, "nome do comando não pode ser nulo");
        }

        Entry entry = name.startsWith(Entry.SEPARATOR)
                ? findEntry(name)
                : findEntry(Entry.resolveName(context, name));

        if (entry == null) {
            return new ExitMessage(ExitCode.COMMAND_NOT_FOUND, name);
        }

        return entry.execute(args);
    }

    /**
     * Encontra uma entrada no contexto atual do shell com base no nome fornecido.
     * 
     * @param name o nome da entrada a ser encontrada.
     * @return a entrada correspondente ao nome fornecido, ou null se não for
     *         encontrada.
     */
    public Entry findEntry(String name) {

        String[] contexts = name.split(Entry.SEPARATOR);
        Entry current = ROOT;

        Console.out.println(Arrays.toString(contexts));

        for (int i = 1; i < contexts.length; i++) {
            Console.out.println(current);

            if (current instanceof Context ctx) {
                current = ctx.find(contexts[i]);
            } else {
                return null;
            }
        }

        Console.out.println(current);
        return current;
    }

    /**
     * Aplica a mensagem de saída do comando executado, atualizando o contexto
     * ou alterando o estado de execução do shell conforme necessário.
     * 
     * @param msg a mensagem de saída do comando executado, que pode conter um
     *            código de saída e uma mensagem associada.
     */
    public void applyMessage(ExitMessage msg) {

        if (msg == null) {
            return;
        }

        switch (msg.code()) {

            case ExitCode.SUCCESS:
                break;

            case ExitCode.CHANGE_CONTEXT:
                this.context = msg.message();
                break;

            case ExitCode.EXIT_SHELL:
                this.running = false;
                break;

            case ExitCode.FAILURE:
                Console.out.println("Erro: " + msg.message());
                Console.out.flush();
                break;

            case ExitCode.INVALID_ARGUMENT:
                Console.out.println("Argumento inválido: " + msg.message());
                Console.out.flush();
                break;

            case ExitCode.COMMAND_NOT_FOUND:
                Console.out.println("Comando não encontrado: " + msg.message());
                Console.out.flush();
                break;

            case ExitCode.PERMISSION_DENIED:
                Console.out.println("Permissão negada: " + msg.message());
                Console.out.flush();
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