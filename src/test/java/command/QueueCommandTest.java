package command;

import command.model.Light;
import command.model.Stereo;
import command.parameter.complex.Command;
import command.parameter.complex.RemoteControl;
import command.parameter.complex.RemoteControlWithUndo;
import command.parameter.complex.impl.*;
import org.junit.Test;

public class QueueCommandTest {

    @Test
    public void testRemoteControl() {
        RemoteControl control = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen Room");
        Stereo stereo = new Stereo("Living Room");

        Command livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);

        Command kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        Command kitchenLightOffCommand = new LightOffCommand(kitchenLight);

        Command livingRoomStereoOnCommand = new StereoOnWithCDCommand(stereo);
        Command livingRoomStereoOffCommand = new StereoOffCommand(stereo);

        control.setCommand(0, livingRoomLightOnCommand, livingRoomLightOffCommand);
        control.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);
        control.setCommand(2, livingRoomStereoOnCommand, livingRoomStereoOffCommand);

        System.out.println(control.toString());

        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);
        control.onButtonWasPushed(1);
        control.offButtonWasPushed(1);
        control.onButtonWasPushed(2);
        control.offButtonWasPushed(2);
        control.onButtonWasPushed(3);
        control.offButtonWasPushed(4);
    }

    @Test
    public void testRemoteControlWithUndo() {
        RemoteControlWithUndo control = new RemoteControlWithUndo();

        Light livingRoomLight = new Light("Living Room");

        Command livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);

        control.setCommand(0,livingRoomLightOnCommand, livingRoomLightOffCommand);

        control.onButtonWasPushed(0);
        control.undoButtonWasPushed();
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);
        control.undoButtonWasPushed();
    }

    @Test
    public void testMacroCommand() {
        RemoteControlWithUndo control = new RemoteControlWithUndo();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen Room");
        Stereo stereo = new Stereo("Living Room");

        Command livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        Command kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        Command livingRoomStereoOnCommand = new StereoOnWithCDCommand(stereo);

        Command livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
        Command kitchenLightOffCommand = new LightOffCommand(kitchenLight);
        Command livingRoomStereoOffCommand = new StereoOffCommand(stereo);

        Command[] onCommands = {livingRoomLightOnCommand, kitchenLightOnCommand, livingRoomStereoOnCommand};
        Command[] offCommands = {livingRoomLightOffCommand, kitchenLightOffCommand, livingRoomStereoOffCommand};

        MacroCommand macroOnCommand = new MacroCommand(onCommands);
        MacroCommand macroOffCommand = new MacroCommand(offCommands);

        control.setCommand(0, macroOnCommand, macroOffCommand);

        System.out.println(control);

        System.out.println("---Pushing Macro On---");
        control.onButtonWasPushed(0);

        System.out.println("---Pushing Macro Off---");
        control.offButtonWasPushed(0);

        System.out.println("---Pushing Macro Undo---");
        control.undoButtonWasPushed();
    }
}
