package posidon.potassium.packets;

import posidon.potassium.universe.block.Block;
import posidon.potassium.universe.generation.Chunk;

import java.io.Serializable;

public class ChunkUpdate implements Serializable {
    private static final long serialVersionUID = 1;
    public int x, y, z;
    public Block[] blocks;
    public ChunkUpdate(Chunk chunk, int x, int y, int z) {
        blocks = chunk.getBlocks();
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
