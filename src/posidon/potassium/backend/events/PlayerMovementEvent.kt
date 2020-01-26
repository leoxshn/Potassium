package posidon.potassium.backend.events;

import posidon.potassium.Window;
import posidon.potassium.backend.Player;
import posidon.potassium.ui.color;

public class PlayerMovementEvent extends PlayerEvent {

    private boolean[] keys = new boolean[4];
    private float direction;
    private int directionY;

    @Override
    public void run(Player player) { try {
        float movX = (float) Math.sin(Math.toRadians(direction)) * player.moveSpeed;
        float movZ = (float) Math.cos(Math.toRadians(direction)) * player.moveSpeed;
        if (keys[0]) {
            player.x -= movX;
            player.z -= movZ;
        } if (keys[1]) {
            player.x += movX;
            player.z += movZ;
        } if (keys[2]) {
            player.x -= movZ;
            player.z += movX;
        } if (keys[3]) {
            player.x += movZ;
            player.z -= movX;
        }
        player.y += directionY * player.jumpHeight;
        Window.print(player.name, color.YELLOW);
        Window.println(" -> " + player.x + "/" + player.y + "/" + player.z, color.GRAY);
    } catch (Exception e) { Window.println(e.toString(), color.RED); } }

    public void setKeys(boolean[] keys) { this.keys = keys; }
    public void setDirection(float direction) { this.direction = direction; }
    public void setDirectionY(int directionY) { this.directionY = directionY; }
}