package command.parameter.simple.impl;

import command.parameter.simple.Command;
import command.model.Light;

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
}
