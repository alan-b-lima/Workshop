package visual.tui.shell.entry;

import visual.tui.shell.Command;
import visual.tui.shell.commands.Empty;

public class CommandEntry extends Entry {
    
    private final Command command;

    public CommandEntry() {
        command = new Empty();
    }

    public CommandEntry(String context, Command command) {
        super(context);
        this.command = command;
    }

    public Command command() {
        return command;
    }
}
