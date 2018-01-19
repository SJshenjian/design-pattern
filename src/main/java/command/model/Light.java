package command.model;

public class Light {
    private String roomName;

    public Light() {};

    public Light(String roomName) {
        this.roomName = roomName;
    }

    public void on() {
        System.out.println(roomName + "light is on");
    }

    public void off() {
        System.out.println(roomName + "light is off");
    }
}
