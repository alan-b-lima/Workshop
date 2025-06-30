package edu.ajan.view.tui.shell.entry;

import java.util.regex.Pattern;

import edu.ajan.view.tui.shell.exit.ExitMessage;

/**
 * Classe abstrata que representa uma entrada.
 * 
 * @author Alan Lima
 * @author Juan Pablo
 */
public abstract class Entry implements Comparable<Entry> {

    /**
     * Padrão regex para validar nomes de contexto.
     */
    private static final Pattern CONTEXT_PATTERN = Pattern.compile("[-/a-z0-9áàâãéêíóôõúüç]*");

    /**
     * Separador de contexto.
     */
    public static final String ROOT_CONTEXT = "";

    /**
     * Separador de contexto.
     */
    public static final String SEPARATOR = "/";

    /**
     * Padrão RegEx usado para separa pode separar um contexto em seus nomes.
     */
    public static final Pattern SEPARATOR_SPLITTER = Pattern.compile(SEPARATOR);

    /**
     * Nome do contexto da entrada.
     */
    private String name;

    /**
     * Contexto pai desta entrada. {@code null} para entrada raiz.
     */
    private Context parent;

    /**
     * Construtor padrão. Construoi uma entrada raiz.
     */
    public Entry() {
        this.name = ROOT_CONTEXT;
        this.parent = null;
    }

    /**
     * Construtor parametrizado.
     * 
     * @param name nome da entrada.
     */
    public Entry(String name) {

        if (!validContextName(name)) {
            throw new IllegalArgumentException("nome de contexto " + name + " é inválido");
        }

        this.name = name;
    }

    /**
     * Retorna o nome do entrada.
     * 
     * @return nome do entrada.
     */
    public String name() {
        return name;
    }

    /**
     * Retorna o nome completo do entrada.
     * 
     * @return nome completo da entrada.
     */
    public String fullName() {

        if (parent == null) {
            return name;
        }

        return resolveName(parent.fullName(), name);
    }

    /**
     * Retorna o contexto pai.
     * 
     * @return contexto pai.
     */
    public Context parent() {
        return parent;
    }

    /**
     * Define o contexto pai.
     * 
     * @param parent contexto pai.
     */
    protected void setParent(Context parent) {
        this.parent = parent;
    }

    /**
     * Executa a lógica específica da entrada.
     * 
     * @param args argumentos fornecidos.
     * @return mensagem de saída.
     */
    public abstract ExitMessage execute(String[] args);

    /**
     * Valida o nome do contexto.
     * 
     * @param context nome do contexto a ser validado.
     * @return true se o nome do contexto for válido, false caso contrário.
     */
    private static boolean validContextName(String context) {
        return CONTEXT_PATTERN.matcher(context).matches();
    }

    public static String resolveName(String original, String... appends) {

        StringBuilder sb = new StringBuilder(original);
        for (String append : appends) {
            sb.append(SEPARATOR).append(append);
        }

        return sb.toString();
    }

    /**
     * Compara {@code this} alfabeticamente com o objeto {@code Entry} fornecido.
     * 
     * @return um valor negativo, zero ou positivo, dependendo da comparação:
     *         {@code this.context < that.context} retorna um valor negativo;
     *         {@code this.context == that.context} retorna zero;
     *         {@code this.context > that.context} retorna um valor positivo.
     */
    @Override
    public int compareTo(Entry that) {

        if (that == null) {
            throw new NullPointerException("entrada não pode ser nula");
        }

        String s0 = this.name;
        String s1 = that.name;

        int minLen = Math.min(s0.length(), s1.length());

        for (int i = 0; i < minLen; i++) {
            int diff = (int) s0.charAt(i) - (int) s1.charAt(i);

            if (diff != 0) {
                return diff;
            } // Se forem iguais compara o tamanho fora do loop
        }

        // Comparação do tamanho caso os caracteres até minLen forem iguais
        return s0.length() - s1.length();
    }

    /**
     * Verifica se dois objetos Entry são iguais com base no nome do contexto.
     * 
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Entry that) {
            return this.compareTo(that) == 0;
        }

        return false;
    }

    /**
     * Retorna a representação textual do contexto.
     * 
     * @return representação textual do contexto.
     */
    @Override
    public String toString() {
        return fullName();
    }
}
