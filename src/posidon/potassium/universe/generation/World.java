package posidon.potassium.universe.generation;

import posidon.potassium.universe.util.Vec3i;
import java.util.HashMap;

public class World {

    private ChunkGenerator generator;
    public final HashMap<Vec3i, Chunk> chunks = new HashMap<>();

    public World(ChunkGenerator generator) { this.generator = generator; }

    public Chunk getChunkFromCoords(float ax, float ay, float az) { return getChunk((int)ax/Chunk.CHUNK_SIZE, (int)ay/Chunk.CHUNK_SIZE, (int)az/Chunk.CHUNK_SIZE); }
    public Chunk getChunk(int cx, int cy, int cz) { return chunks.get(new Vec3i(cx, cy, cz)); }

    public void genChunkFromCoords(float ax, float ay, float az) { genChunk((int)ax/Chunk.CHUNK_SIZE, (int)ay/Chunk.CHUNK_SIZE, (int)az/Chunk.CHUNK_SIZE); }
    public Chunk genChunk(int cx, int cy, int cz) {
        Chunk chunk = generator.genChunk(cx * Chunk.CHUNK_SIZE, cy * Chunk.CHUNK_SIZE, cz * Chunk.CHUNK_SIZE);
        chunks.put(new Vec3i(cx, cy, cz), chunk);
        return chunk;
    }
}
