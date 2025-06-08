package visual.tui.shell.entry;

import java.util.Arrays;

import visual.tui.shell.Command;
import visual.tui.shell.exit.ExitCode;
import visual.tui.shell.exit.ExitMessage;

public class ContextEntry extends Entry {

    private final Entry[] entries;

    private final Command command;

    public ContextEntry() {
        entries = new Entry[0];
        command = (String[] _) -> new ExitMessage(ExitCode.ENTER_CONTEXT, context());
    }

    public ContextEntry(Entry[] entries) {
        this("", entries);
    }

    public ContextEntry(String context, Entry[] entries) {
        super(context);

        Arrays.sort(entries);
        this.entries = entries;
        this.command = (String[] _) -> new ExitMessage(ExitCode.ENTER_CONTEXT, context);
    }

    public Entry[] entries() {
        return entries;
    }

    public int length() {
        return entries.length;
    }

    public Entry at(int index) {
        return entries[index];
    }

    @Override
    public Command command() {
        return command;
    }
}
