package posidon.potassium.backend.events

import posidon.potassium.Window
import posidon.potassium.backend.Player
import posidon.potassium.ui.Color

class PlayerMovementEvent : PlayerEvent() {
    private var keys = BooleanArray(4)
    private var direction = 0f
    private var directionY = 0
    public override fun run(player: Player) {
        try {
            val movX = Math.sin(Math.toRadians(direction.toDouble())).toFloat() * player.moveSpeed
            val movZ = Math.cos(Math.toRadians(direction.toDouble())).toFloat() * player.moveSpeed
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
            Window.print(player.playerName, Color.YELLOW)
            Window.println(" -> " + player.x + "/" + player.y + "/" + player.z, Color.GRAY)
        } catch (e: Exception) {
            Window.println(e.toString(), Color.RED)
        }
    }

    fun setKeys(keys: BooleanArray) {
        this.keys = keys
    }

    fun setDirection(direction: Float) {
        this.direction = direction
    }

    fun setDirectionY(directionY: Int) {
        this.directionY = directionY
    }
}