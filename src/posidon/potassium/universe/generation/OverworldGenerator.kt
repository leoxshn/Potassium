package posidon.potassium.universe.generation;

import posidon.potassium.universe.block.Block;

public class OverworldGenerator extends ChunkGenerator {

    private OpenSimplexNoise openSimplexNoise;

    public OverworldGenerator(int seed) {
        super(seed);
        openSimplexNoise = new OpenSimplexNoise(seed);
    }

    @Override
    protected Chunk genChunk(int absX, int absY, int absZ) {
        Chunk chunk = new Chunk();
        for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
            for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
                for (int z = 0; z < Chunk.CHUNK_SIZE; z++) {
                    int genHeight = genHeight(absX + x, absZ + z);
                    if (absY + y <= genHeight) chunk.setBlock(absY + y > 5 ? Block.grass() : Block.stone(), x, y, z);
                }
            }
        }
        return chunk;
    }

    private int genHeight(int x, int z) {
        return 20 +
                (int)(openSimplexNoise.eval(x/150f, z/150f) * 48) +
                (int)(openSimplexNoise.eval(x/80f, z/80f) * 24) +
                (int)(openSimplexNoise.eval(x/40f, z/40f) * 12) +
                (int)(openSimplexNoise.eval(x/20f, z/20f) * 6) +
                (int)(openSimplexNoise.eval(x/10f, z/10f) * 3);
    }
}
