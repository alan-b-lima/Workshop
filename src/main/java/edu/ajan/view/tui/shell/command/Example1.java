package edu.ajan.view.tui.shell.command;

import edu.ajan.view.tui.shell.entry.Command;
import edu.ajan.view.tui.shell.exit.ExitMessage;

public class Example1 extends Command {

    public Example1(String name) {
        super(name);
    }

    @Override
    public ExitMessage execute(String[] args) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
