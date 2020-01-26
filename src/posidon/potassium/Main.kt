package posidon.potassium

import posidon.potassium.Window.Companion.create
import posidon.potassium.Window.Companion.println
import posidon.potassium.backend.Player
import posidon.potassium.backend.PlayerHandler
import posidon.potassium.ui.Color
import posidon.potassium.universe.PlanetWorld
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.URL

class Main : Runnable {
    override fun run() {
        create()
        println("Setting up networking stuff...", Color.WHITE)
        try {
            ServerSocket(port).use { serverSocket ->
                Thread(PlanetWorld(), "world").start()
                while (running) {
                    try {
                        val clientSocket = serverSocket.accept()
                        Player(clientSocket).start()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                println("Killing server...")
                kill()
                try {
                    serverSocket.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun kill() {
        for (player in PlayerHandler.values()) player.disconnect()
    }

    companion object {
        var key = "leoleo"
        private const val port = 2512
        var running = true

        val extIP: String
            get() {
                var out: String
                try {
                    val ipUrl = URL("http://checkip.amazonaws.com")
                    var `in`: BufferedReader? = null
                    try {
                        `in` = BufferedReader(InputStreamReader(ipUrl.openStream()))
                        out = `in`.readLine()
                    } catch (e: Exception) {
                        out = "error: " + e.message
                    }
                    `in`?.close()
                } catch (e: Exception) {
                    out = "error: " + e.message
                }
                return out
            }
    }
}


fun main() = Thread(Main()).start()