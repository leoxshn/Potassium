package posidon.potassium.universe

import posidon.potassium.Main
import posidon.potassium.Window
import posidon.potassium.backend.PlayerHandler
import posidon.potassium.packets.ChunkUpdate
import posidon.potassium.ui.Color
import posidon.potassium.universe.generation.Chunk
import posidon.potassium.universe.generation.Overworld
import java.util.concurrent.TimeUnit

class PlanetWorld : Runnable {

    override fun run() {
        ////START/////////////////////////////////////
        Window.println("Starting the world...", Color.WHITE)

        Window.println("Done!", Color.GREEN)
        ////LOOP//////////////////////////////////////
        var lastTime: Long = System.nanoTime()
        val amountOfTicks: Double = 60.0
        val ns: Double = 1000000000.0 / amountOfTicks
        var delta: Double = 0.0
        while (Main.running) {
            val now: Long = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now
            while (delta >= 1) {
                tick()
                delta--
            }
            TimeUnit.NANOSECONDS.sleep((ns - (now - lastTime)).toLong())
        }
        //////////////////////////////////////////////
    }

    private fun tick() {
        Globals.tick()
        for (player in PlayerHandler.values()) {
            player.runMovementEvents()
            val cx: Int = player.x.toInt() / Chunk.CHUNK_SIZE
            val cy: Int = player.y.toInt() / Chunk.CHUNK_SIZE
            val cz: Int = player.z.toInt() / Chunk.CHUNK_SIZE
            val renderDistance = 1
            val renderHeight = 1
            for (x in 0..renderDistance) for (z in 0..renderDistance) for (y in 0..renderHeight) {
                if (overworld.getChunk(cx + x, cy + y, cz + z) == null)
                    player.send(ChunkUpdate(overworld.genChunk(cx + x, cy + y, cz + z), cx + x, cy + y, cz + z))
                if (overworld.getChunk(cx - x, cy - y, cz - z) == null)
                    player.send(ChunkUpdate(overworld.genChunk(cx - x, cy - y, cz - z), cx - x, cy - y, cz - z))
            }
        }
    }

    companion object {
        val overworld: Overworld = Overworld()
    }
}
