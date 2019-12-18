package posidon.potassium.packets;

import posidon.potassium.backend.Player;

import java.io.Serializable;

public class PlayerJoinPacket implements Serializable {
    private static final long serialVersionUID = 1;
    public int id;
    public String name;
    public Player player;

    public PlayerJoinPacket(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
