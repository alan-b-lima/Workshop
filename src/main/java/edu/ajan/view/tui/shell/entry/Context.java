package edu.ajan.view.tui.shell.entry;

import java.util.Arrays;
import java.util.Comparator;

import edu.ajan.view.tui.shell.exit.ExitCode;
import edu.ajan.view.tui.shell.exit.ExitMessage;

public class Context extends Entry {

    private static final Comparator<Entry> ENTRY_COMPARATOR = new Comparator<Entry>() {

        @Override
        public int compare(Entry e0, Entry e1) {

            if (e0 instanceof Context == e1 instanceof Context) {
                return e0.compareTo(e1);
            }

            return e0 instanceof Context ? -1 : 1;
        }
    };

    private final Entry[] entries;

    public Context(Entry... entries) {
        this("", entries);
    }

    public Context(String name, Entry... entries) {
        super(name);

        for (Entry entry : entries) {
            entry.setParent(this);
        }

        this.entries = entries;
        Arrays.sort(this.entries, ENTRY_COMPARATOR);
    }

    public Entry[] entries() {
        return entries;
    }

    public Entry find(String name) {
        for (Entry entry : entries) {
            if (entry.name().equals(name)) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public ExitMessage execute(String[] args) {
        return new ExitMessage(ExitCode.CHANGE_CONTEXT, fullName());
    }

    @Override
    public String toString() {

        String stringifiedEntries = Arrays.toString(entries);
        String superName = super.toString();

        return superName + "=" + stringifiedEntries;
    }
}
