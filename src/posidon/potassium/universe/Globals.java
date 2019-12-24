package posidon.potassium.universe;

import posidon.potassium.backend.Sender;
import posidon.potassium.packets.Packet;

public class Globals {

    private static double time;
    private static int timeSpeed = 1;

    private static final int MAX_TIME = 24000;

    static void tick() { time = (time < MAX_TIME) ? time + timeSpeed : 0; }

    public static boolean isNight() {
        return false;
    }

    public static boolean isDay() {
        return false;
    }

    public static double getTime() { return time; }
    public static void setTime(double time) {
        Globals.time = time;
        Packet update = new Packet();
        update.put("time", Globals.time);
        Sender.sendToAllPlayers(update);
    }

    public static int getTimeSpeed() { return timeSpeed; }
    public static void setTimeSpeed(int timeSpeed) {
        Globals.timeSpeed = timeSpeed;
        Packet update = new Packet();
        update.put("timeSpeed", Globals.timeSpeed);
        Sender.sendToAllPlayers(update);
    }
}
