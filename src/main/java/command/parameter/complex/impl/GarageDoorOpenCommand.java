package command.parameter.complex.impl;

import command.model.GarageDoor;
import command.parameter.complex.Command;

public class GarageDoorOpenCommand implements Command {
    private GarageDoor door;

    public GarageDoorOpenCommand() {}

    public GarageDoorOpenCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
    }

    @Override
    public void undo() {
        door.down();
    }
}
