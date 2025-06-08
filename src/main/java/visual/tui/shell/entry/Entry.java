package visual.tui.shell.entry;

import java.util.Comparator;
import java.util.regex.Pattern;

import visual.tui.shell.Command;

/**
 * Classe abstrata que representa a união das classes CommandEntry e
 * ContextEntry
 * 
 * @author Alan Lima
 * @author Juan Pablo
 * 
 * @see CommandEntry
 * @see ContextEntry
 */
public abstract class Entry implements Comparable<Entry>, Comparator<Entry> {

    /**
     * Nome do contexto ao qual a entrada pertence.
     */
    private final String context;

    /**
     * Construtor padrão.
     */
    public Entry() {
        context = "";
    }

    /**
     * Construtor que recebe o nome do contexto.
     * 
     * @param context nome do contexto.
     * @throws IllegalArgumentException se o nome do contexto for inválido.
     */
    public Entry(String context) {
        if (validadeContextName(context) == false) {
            throw new IllegalArgumentException("nome de contexto inválido!");
        }

        this.context = context;
    }

    /**
     * Padrão regex para validar nomes de contexto.
     */
    private static final Pattern CONTEXT_PATTERN = Pattern.compile("[-a-z0-9]*");

    /**
     * Retorna o nome do contexto ao qual a entrada pertence.
     * 
     * @return nome do contexto.
     */
    public String context() {
        return context;
    }

    /**
     * Executa a unidade de execução associada ao comando ou contexto.
     */
    public abstract Command command();

    /**
     * Valida o nome do contexto.
     * 
     * @param context nome do contexto a ser validado.
     * @return true se o nome do contexto for válido, false caso contrário.
     */
    private static boolean validadeContextName(String context) {
        return CONTEXT_PATTERN.matcher(context).matches();
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
        return compare(this, that);
    }

    /**
     * Compara dois objetos Entry alfabeticamente com base no nome do contexto.
     * 
     * @return um valor negativo, zero ou positivo, dependendo da comparação:
     *         {@code o0.context < o1.context} retorna um valor negativo;
     *         {@code o0.context == o1.context} retorna zero;
     *         {@code o0.context > o1.context} retorna um valor positivo.
     */
    @Override
    public int compare(Entry o0, Entry o1) {

        if (o0 == null || o1 == null) {
            throw new NullPointerException("entradas não podem ser nulas");
        }
        
        String s0 = o0.context;
        String s1 = o1.context;

        int len0 = s0.length();
        int len1 = s1.length();
        
        int minLen = Math.min(len0, len1);

        for (int i = 0; i < minLen; i++) {
            char c0 = s0.charAt(i);
            char c1 = s1.charAt(i);
            if (c0 - c1 != 0) {
                return c0 - c1;
            } // se forem iguais compara o tamanho fora do loop
        }

        // comparação do tamanho caso os caracteres até minLen forem iguais
        return len0 - len1;
    }

    /**
     * Verifica se dois objetos Entry são iguais com base no nome do contexto.
     * 
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that instanceof Entry) {
            return compare(this, (Entry) that) == 0;
        }

        return false;
    }
}
