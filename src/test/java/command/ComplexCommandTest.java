package command;

import command.parameter.simple.Command;
import command.parameter.simple.impl.GarageDoorOpenCommand;
import command.model.GarageDoor;
import command.model.Light;
import command.parameter.simple.impl.LightOnCommand;
import command.parameter.simple.SimpleRemoteControl;
import org.junit.Test;

public class ComplexCommandTest {

    @Test
    public void testParameterCommand() {
        Light light = new Light("Living Room");
        GarageDoor door = new GarageDoor();

        Command lightOnCommand = new LightOnCommand(light);
        Command garageDoorOpenCommand = new GarageDoorOpenCommand(door);

        SimpleRemoteControl control = new SimpleRemoteControl();

        control.setCommand(lightOnCommand);
        control.buttonWasPressed();

        control.setCommand(garageDoorOpenCommand);
        control.buttonWasPressed();
    }
}
