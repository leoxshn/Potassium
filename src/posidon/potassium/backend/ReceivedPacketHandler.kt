package posidon.potassium.backend

import posidon.potassium.Console
import posidon.potassium.backend.events.PlayerMovementEvent
import posidon.potassium.packets.*

object ReceivedPacketHandler {
    fun onEvent(event: Any?, id: Int) = when (event) {
        is Packet -> {
            val movementEvent = PlayerMovementEvent()
            if (event.containsKey("walk")) {
                movementEvent.setDirection(event["walk"] as Float)
                movementEvent.setKeys(event["keys"] as BooleanArray)
            }
            if (event.containsKey("fly")) movementEvent.setDirectionY(if (event["fly"] as Int > 0) 1 else -1)
            Players[id]!!.addMovementEvent(movementEvent)
        }
        is PlayerJoinPacket -> {
            Players[event.id] = event.player
            Console.beforeCmdLine {
                Console.printInfo(event.name, " joined")
            }
            event.player.send(InitInfoPacket(event.player))
        }
        is ChatMessage -> Console.println(event.message)
        else -> Console.beforeCmdLine {
            Console.printProblem(Players[id]!!.name, " sent an unknown packet!")
        }
    }
}