package posidon.potassium.universe.generation

import posidon.potassium.universe.util.Vec3i
import java.util.*

open class World(private val generator: ChunkGenerator) {

    val chunks = HashMap<Vec3i, Chunk?>()

    fun getChunkFromCoords(ax: Float, ay: Float, az: Float): Chunk? {
        return getChunk(ax.toInt() / Chunk.CHUNK_SIZE, ay.toInt() / Chunk.CHUNK_SIZE, az.toInt() / Chunk.CHUNK_SIZE)
    }

    fun getChunk(cx: Int, cy: Int, cz: Int): Chunk? {
        return chunks[Vec3i(cx, cy, cz)]
    }

    fun genChunkFromCoords(ax: Float, ay: Float, az: Float) {
        genChunk(ax.toInt() / Chunk.CHUNK_SIZE, ay.toInt() / Chunk.CHUNK_SIZE, az.toInt() / Chunk.CHUNK_SIZE)
    }

    fun genChunk(cx: Int, cy: Int, cz: Int): Chunk {
        val chunk = generator.genChunk(cx * Chunk.CHUNK_SIZE, cy * Chunk.CHUNK_SIZE, cz * Chunk.CHUNK_SIZE)
        chunks[Vec3i(cx, cy, cz)] = chunk
        return chunk
    }
}