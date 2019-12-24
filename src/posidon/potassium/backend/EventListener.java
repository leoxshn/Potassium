package posidon.potassium.backend;

import posidon.potassium.Commands;
import posidon.potassium.Main;
import posidon.potassium.Window;
import posidon.potassium.backend.events.PlayerMovementEvent;
import posidon.potassium.packets.*;
import posidon.potassium.ui.color;

public class EventListener {
    public static void onEvent(Object event, int id) {
        if (event instanceof Packet) {
            Packet packet = (Packet) event;
            PlayerMovementEvent movementEvent = new PlayerMovementEvent();
            if (packet.containsKey("walk")) {
                movementEvent.setDirection((float) packet.get("walk"));
                movementEvent.setKeys((boolean[]) packet.get("keys"));
            } if (packet.containsKey("fly")) movementEvent.setDirectionY(((int) packet.get("fly") > 0 ? 1 : -1));
            PlayerHandler.get(id).addMovementEvent(movementEvent);
        } else if (event instanceof PlayerJoinPacket) {
            PlayerJoinPacket packet = (PlayerJoinPacket) event;
            PlayerHandler.put(packet.id, packet.player);
            Window.print(packet.name, color.YELLOW);
            Window.println(" joined", color.GRAY);
            packet.player.send(new InitInfoPacket(packet.player));
        } else if (event instanceof ChatMessage) {
            ChatMessage packet = (ChatMessage) event;
            Window.println(packet.message);
        } else if (event instanceof AuthCommand) {
            AuthCommand authCommand = (AuthCommand) event;
            if (authCommand.key.equals(Main.key)) new Commands().onCommand(authCommand.cmd);
            else Window.println("Invalid key!", color.RED);
        }
    }
}
