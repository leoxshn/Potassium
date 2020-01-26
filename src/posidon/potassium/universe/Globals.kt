package posidon.potassium.universe

import posidon.potassium.backend.Sender
import posidon.potassium.packets.Packet

object Globals {

    @JvmStatic
    var time = 0.0
        set(value) {
            field = value
            val update = Packet()
            update["time"] = time
            Sender.sendToAllPlayers(update)
        }

    var timeSpeed = 1
        set(value) {
            field = value
            val update = Packet()
            update["timeSpeed"] = timeSpeed
            Sender.sendToAllPlayers(update)
        }

    private const val MAX_TIME = 24000

    fun tick() {
        time = if (time < MAX_TIME) time + timeSpeed else 0.0
    }

    val isNight: Boolean
        get() = false

    val isDay: Boolean
        get() = false
}