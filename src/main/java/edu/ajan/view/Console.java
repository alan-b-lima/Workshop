package edu.ajan.view;

import java.io.IOException;
import java.io.PrintWriter;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

/**
 * Classe utilitária para interagir com o console do terminal.
 *
 * @author Alan Lima
 */
public final class Console {

    /**
     * Terminal do console.
     */
    public static final Terminal terminal = newTerminal();

    /**
     * PrintWriter para escrever no console.
     */
    public static final PrintWriter out = newOut(terminal);

    /**
     * LineReader para ler entradas do console.
     */
    public static final LineReader in = newIn(terminal);

    /**
     * Construtor privado.
     */
    private Console() {

    }

    /**
     * Construtor do terminal.
     * 
     * @return terminal construído.
     * 
     * @throws RuntimeException se ocorrer um erro ao criar o terminal.
     */
    private static Terminal newTerminal() {
        try {
            return TerminalBuilder.builder().system(true).build();
        } catch (IOException err) {
            throw new RuntimeException("erro ao criar terminal: " + err.getMessage(), err);
        }
    }

    /**
     * Cria um novo PrintWriter para o terminal.
     * 
     * @param terminal terminal a ser usado.
     * @return PrintWriter para o terminal.
     */
    private static PrintWriter newOut(Terminal terminal) {
        PrintWriter writer = terminal.writer();
        writer.flush();
        return writer;
    }

    /**
     * Cria um novo LineReader para o terminal.
     * 
     * @param terminal terminal a ser usado.
     * @return LineReader para o terminal.
     */
    private static LineReader newIn(Terminal terminal) {
        return LineReaderBuilder.builder().terminal(terminal).build();
    }
}
