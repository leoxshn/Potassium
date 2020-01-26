package posidon.potassium.universe.generation

import posidon.potassium.universe.block.Block

class OverworldGenerator(seed: Long) : ChunkGenerator(seed) {

    private val openSimplexNoise: OpenSimplexNoise = OpenSimplexNoise(seed)

    override fun genChunk(absX: Int, absY: Int, absZ: Int): Chunk {
        val chunk = Chunk()
        for (x in 0 until Chunk.CHUNK_SIZE) {
            for (y in 0 until Chunk.CHUNK_SIZE) {
                for (z in 0 until Chunk.CHUNK_SIZE) {
                    val genHeight = genHeight(absX + x, absZ + z)
                    if (absY + y <= genHeight) chunk.setBlock(if (absY + y > 5) Block.grass() else Block.stone(), x, y, z)
                }
            }
        }
        return chunk
    }

    private fun genHeight(x: Int, z: Int): Int {
        return 20 +
                (openSimplexNoise.eval(x / 150f.toDouble(), z / 150f.toDouble()) * 48).toInt() +
                (openSimplexNoise.eval(x / 80f.toDouble(), z / 80f.toDouble()) * 24).toInt() +
                (openSimplexNoise.eval(x / 40f.toDouble(), z / 40f.toDouble()) * 12).toInt() +
                (openSimplexNoise.eval(x / 20f.toDouble(), z / 20f.toDouble()) * 6).toInt() +
                (openSimplexNoise.eval(x / 10f.toDouble(), z / 10f.toDouble()) * 3).toInt()
    }
}