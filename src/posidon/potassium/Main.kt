package posidon.potassium

import posidon.potassium.backend.Player
import posidon.potassium.backend.Players
import posidon.potassium.tools.print
import posidon.potassium.universe.PlanetWorld
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.URL

class Main : Runnable {

    override fun run() {
        Thread(Console()).start()
        Console.beforeCmdLine {
            Console.println("Setting up networking stuff...")
        }
        try {
            socket = ServerSocket(port)
            Thread(PlanetWorld(), "world").start()
            while (running) {
                try {
                    val clientSocket = socket.accept()
                    Player(clientSocket).start()
                } catch (e: Exception) { e.print() }
            }
            for (player in Players) player.disconnect()
            try { socket.close() }
            catch (e: Exception) { e.print() }
        } catch (e: IOException) { e.print() }
    }

    companion object {

        lateinit var socket: ServerSocket

        private const val port = 2512
        var running = true

        val extIP: String?
            get() {
                var out: String? = null
                try {
                    val ipUrl = URL("http://checkip.amazonaws.com")
                    var input: BufferedReader? = null
                    try {
                        input = BufferedReader(InputStreamReader(ipUrl.openStream()))
                        out = input.readLine()
                    } catch (e: Exception) {}
                    input?.close()
                } catch (e: Exception) {}
                return out
            }
    }
}

fun main() = Thread(Main()).start()

fun stop() {
    Console.println("Stopping server...")
    Main.running = false
    for (player in Players) player.disconnect()
    try { Main.socket.close() }
    catch (e: Exception) { e.print() }
}

