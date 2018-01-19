package command.parameter.complex.impl;

import command.model.GarageDoor;
import command.parameter.complex.Command;

public class GarageDoorCloseCommand implements Command {
    private GarageDoor door;

    public GarageDoorCloseCommand() {}

    public GarageDoorCloseCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.down();
    }

    @Override
    public void undo() {
        door.up();
    }
}
