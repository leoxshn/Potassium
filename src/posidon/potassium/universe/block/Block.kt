package posidon.potassium.universe.block

import java.io.Serializable

class Block(val name: String, val hardness: Float, val emission: Float) : Serializable {

    fun tick() {}

    companion object {
        private const val serialVersionUID: Long = 1
        fun wood() = Block("wood", 0.5f, 0f)
        fun stone() = Block("stone", 1f, 0f)
        fun grass() = Block("grass", 0.1f, 0f)
    }

}