package visual.tui.shell.commands;

import visual.tui.shell.Command;
import visual.tui.shell.exit.ExitCode;
import visual.tui.shell.exit.ExitMessage;

public class Exit implements Command {

    @Override
    public ExitMessage execute(String[] args) {
        return new ExitMessage(ExitCode.EXIT_CONTEXT);        
    }
}
