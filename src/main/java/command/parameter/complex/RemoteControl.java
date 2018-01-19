package command.parameter.complex;

import command.parameter.complex.impl.NoCommand;

public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();

        for (int i = 0; i < onCommands.length; i++ ) {
            //避免onButtonWasPushed/offButtonWasPushed空检查
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("------Remote Control------\n");
        for (int i = 0; i < onCommands.length; i++) {
            builder.append("[slot" + i + "]" + onCommands[i].getClass().getName() + "           " + offCommands[i].getClass().getName() + "\n");
        }
        return builder.toString();
    }
}
