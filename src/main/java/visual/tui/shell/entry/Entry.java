package visual.tui.shell.entry;

import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Classe abstrata que representa a união das classes CommandEntry e
 * ContextEntry
 */
public abstract class Entry implements Comparable<Entry>, Comparator<Entry> {

    public final String context;

    public Entry() {
        context = "";
    }

    public Entry(String context) {
        if (validadeContextName(context) == false) {
            throw new IllegalArgumentException("nome de contexto inválido!");
        }

        this.context = context;
    }

    private static final Pattern CONTEXT_PATTERN = Pattern.compile("[-a-z0-9]+");

    private static boolean validadeContextName(String context) {
        return CONTEXT_PATTERN.matcher(context).matches();
    }

    @Override
    public int compareTo(Entry that) {
        return compare(this, that);
    }

    @Override
    public int compare(Entry o0, Entry o1) {

        int length = Math.min(o0.context.length(), o1.context.length());

        for (int i = 0; i < length; i++) {
            int diff = (int) o0.context.charAt(i) - (int) o1.context.charAt(i);
            
            if (diff != 0) {
                return diff;
            }
        }

        return o0.context.length() - o1.context.length();
    }

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
