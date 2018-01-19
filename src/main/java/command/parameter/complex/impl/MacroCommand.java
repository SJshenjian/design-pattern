package command.parameter.complex.impl;

import command.parameter.complex.Command;

public class MacroCommand implements Command {
    private Command[] commands;

    public MacroCommand() {}

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commands) {
            command.undo();
        }
    }
}
