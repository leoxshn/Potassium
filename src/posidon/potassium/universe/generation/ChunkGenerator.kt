package posidon.potassium.universe.generation

import java.util.*

abstract class ChunkGenerator(protected val seed: Long) {

    protected val random: Random = Random(seed)

    abstract fun genChunk(x: Int, y: Int, z: Int): Chunk

}