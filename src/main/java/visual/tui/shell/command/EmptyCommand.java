package visual.tui.shell.command;

import visual.tui.shell.Command;
import visual.tui.shell.exit.ExitCode;
import visual.tui.shell.exit.ExitMessage;

public class EmptyCommand implements Command {

    @Override
    public ExitMessage execute(String[] args) {
        System.out.println("eu sou o commando vazio, lembre de me apagar depois");
        return new ExitMessage(ExitCode.SUCCESS);
    }
}
