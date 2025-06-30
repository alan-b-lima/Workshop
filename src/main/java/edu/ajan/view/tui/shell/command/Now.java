package edu.ajan.view.tui.shell.command;

import edu.ajan.model.workshop.date.Dates;
import edu.ajan.view.Console;
import edu.ajan.view.tui.shell.entry.Command;
import edu.ajan.view.tui.shell.exit.ExitCode;
import edu.ajan.view.tui.shell.exit.ExitMessage;

public class Now extends Command {

    public Now() {
        super("agora");
    }

    @Override
    public ExitMessage execute(String[] args) {
        Console.out.println(Dates.formatAsDateTime(Dates.now()));
        return new ExitMessage(ExitCode.SUCCESS);
    }
}
