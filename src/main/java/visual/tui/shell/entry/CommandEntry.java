package visual.tui.shell.entry;

import visual.tui.shell.Command;
import visual.tui.shell.Commands;

public class CommandEntry extends Entry {
    
    private final Command command;

    public CommandEntry() {
        command = Commands.EMPTY_COMMAND;
    }

    public CommandEntry(String context, Command command) {
        super(context);
        this.command = command;
    }

    public void execute(String[] args) {
        command.execute(args);
    }
}
