package posidon.potassium.backend.events

import posidon.potassium.Console
import posidon.potassium.backend.Player
import posidon.potassium.tools.print
import kotlin.math.cos
import kotlin.math.sin

class PlayerMovementEvent : PlayerEvent() {
    private var keys = BooleanArray(4)
    private var direction = 0f
    private var directionY = 0

    override fun run(player: Player) {
        try {
            val movX = sin(Math.toRadians(direction.toDouble())).toFloat() * player.moveSpeed
            val movZ = cos(Math.toRadians(direction.toDouble())).toFloat() * player.moveSpeed
            if (keys[0]) {
                player.x -= movX
                player.z -= movZ
            }
            if (keys[1]) {
                player.x += movX
                player.z += movZ
            }
            if (keys[2]) {
                player.x -= movZ
                player.z += movX
            }
            if (keys[3]) {
                player.x += movZ
                player.z -= movX
            }
            player.y += directionY * player.jumpHeight
            Console.beforeCmdLine {
                Console.printInfo(player.playerName,
                        " -> " + player.x + "/" + player.y + "/" + player.z)
            }
        } catch (e: Exception) { e.print() }
    }

    fun setKeys(keys: BooleanArray) { this.keys = keys }
    fun setDirection(direction: Float) { this.direction = direction }
    fun setDirectionY(directionY: Int) { this.directionY = directionY }
}