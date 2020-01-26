package posidon.potassium.backend

import posidon.potassium.Commands
import posidon.potassium.Main
import posidon.potassium.Window
import posidon.potassium.backend.events.PlayerMovementEvent
import posidon.potassium.packets.*
import posidon.potassium.ui.Color

object EventListener {
    fun onEvent(event: Any?, id: Int) {
        if (event is Packet) {
            val packet = event
            val movementEvent = PlayerMovementEvent()
            if (packet.containsKey("walk")) {
                movementEvent.setDirection(packet["walk"] as Float)
                movementEvent.setKeys(packet["keys"] as BooleanArray)
            }
            if (packet.containsKey("fly")) movementEvent.setDirectionY(if (packet["fly"] as Int > 0) 1 else -1)
            PlayerHandler.get(id)!!.addMovementEvent(movementEvent)
        } else if (event is PlayerJoinPacket) {
            val packet = event
            PlayerHandler.put(packet.id, packet.player)
            Window.print(packet.name, Color.YELLOW)
            Window.println(" joined", Color.GRAY)
            packet.player.send(InitInfoPacket(packet.player))
        } else if (event is ChatMessage) {
            Window.println(event.message)
        } else if (event is AuthCommand) {
            val authCommand = event
            if (authCommand.key == Main.key) Commands().onCommand(authCommand.cmd) else Window.println("Invalid key!", Color.RED)
        }
    }
}