package posidon.potassium.universe.generation;

import posidon.potassium.universe.block.Block;

public class Chunk {
    public static final int CHUNK_SIZE = 12;
    private Block[] blocks = new Block[CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE];
    public Block getBlock(int x, int y, int z) { return blocks[x * CHUNK_SIZE * CHUNK_SIZE + y * CHUNK_SIZE + z]; }
    public void setBlock(Block block, int x, int y, int z) { blocks[x * CHUNK_SIZE * CHUNK_SIZE + y * CHUNK_SIZE + z] = block; }

    public Block[] getBlocks() { return blocks; }
}
