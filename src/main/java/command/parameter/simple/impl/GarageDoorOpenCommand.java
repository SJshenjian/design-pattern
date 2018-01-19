package command.parameter.simple.impl;

import command.parameter.simple.Command;
import command.model.GarageDoor;

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
}
