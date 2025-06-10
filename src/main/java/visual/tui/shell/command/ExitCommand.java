package visual.tui.shell.command;

import visual.tui.shell.Command;
import visual.tui.shell.exit.ExitCode;
import visual.tui.shell.exit.ExitMessage;

public class ExitCommand implements Command {

    @Override
    public ExitMessage execute(String[] args) {

        if (args.length > 1 && args[1].equals("tudo")) {
            return new ExitMessage(ExitCode.EXIT_SHELL);
        }

        return new ExitMessage(ExitCode.EXIT_CONTEXT);
    }
}
