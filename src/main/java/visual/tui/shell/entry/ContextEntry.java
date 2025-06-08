package visual.tui.shell.entry;

import java.util.Arrays;

public class ContextEntry extends Entry {

    public final Entry[] entries;

    public ContextEntry() {
        entries = new Entry[0];
    }

    public ContextEntry(Entry[] entries) {
        Arrays.sort(entries);
        this.entries = entries;
    }

    public ContextEntry(String context, Entry[] entries) {
        super(context);

        Arrays.sort(entries);
        this.entries = entries;
    }

    public int length() {
        return entries.length;
    }

    public Entry at(int index) {
        return entries[index];
    }
}
