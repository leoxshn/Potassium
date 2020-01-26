package posidon.potassium.universe.generation

import posidon.potassium.universe.block.Block

class Chunk {

    @JvmField
    val blocks = arrayOfNulls<Block>(CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE)

    fun getBlock(x: Int, y: Int, z: Int): Block? {
        return blocks[x * CHUNK_SIZE * CHUNK_SIZE + y * CHUNK_SIZE + z]
    }

    fun setBlock(block: Block?, x: Int, y: Int, z: Int) {
        blocks[x * CHUNK_SIZE * CHUNK_SIZE + y * CHUNK_SIZE + z] = block
    }

    companion object {
        const val CHUNK_SIZE = 12
    }
}