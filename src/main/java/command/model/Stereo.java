package command.model;

public class Stereo {
    private String roomName;

    public Stereo() {};

    public Stereo(String roomName) {
        this.roomName = roomName;
    }

    public void on() {
        System.out.println(roomName + "stereo is on");
    }

    public void setCD() {
        System.out.println(roomName + "stereo is set for CD input");
    }

    public void setVolume(int number) {
        System.out.println(roomName + "stereo volume set to " + number);
    }

    public void off() {
        System.out.println(roomName + "stereo is off");
    }
}
