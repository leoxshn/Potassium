package posidon.potassium.universe

import java.io.Serializable
import java.util.*

class WorldSave : Serializable {

    var time: Double = Globals.time

    companion object {
        private const val serialVersionUID: Long = 1
        // x, y, z, health, uranium
        var playerInfo = HashMap<Int, IntArray>()
    }
}