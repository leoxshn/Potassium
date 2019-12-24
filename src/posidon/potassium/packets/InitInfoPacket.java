package posidon.potassium.packets;

import posidon.potassium.backend.Player;
import posidon.potassium.universe.Globals;

import java.io.Serializable;

public class InitInfoPacket implements Serializable {
    private static final long serialVersionUID = 1;
    public double time;
    public float x, y, z, moveSpeed, jumpHeight;

    public InitInfoPacket(Player player) {
        time = Globals.getTime();
        moveSpeed = player.moveSpeed;
        jumpHeight = player.jumpHeight;
        x = player.x;
        y = player.y;
        z = player.z;
    }
}
