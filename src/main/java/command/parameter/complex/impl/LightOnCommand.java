package command.parameter.complex.impl;

import command.model.Light;
import command.parameter.complex.Command;

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand() {};

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
