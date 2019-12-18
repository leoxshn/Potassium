package posidon.potassium.universe.generation;

import java.util.Random;

public abstract class ChunkGenerator {
    protected final Random random;
    protected final long seed;
    public ChunkGenerator(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }
    protected abstract Chunk genChunk(int x, int y, int z);
}
