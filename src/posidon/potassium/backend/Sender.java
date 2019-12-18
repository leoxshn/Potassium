package posidon.potassium.backend;

public class Sender {
    public static void sendToAllPlayers(Object obj) {
        for (Player player : PlayerHandler.values()) player.send(obj);
    }
}
