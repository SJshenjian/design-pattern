package command.parameter.complex.impl;

import command.model.Light;
import command.parameter.complex.Command;

public class LightOffCommand implements Command{
    private Light light;

    public LightOffCommand() {};

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
