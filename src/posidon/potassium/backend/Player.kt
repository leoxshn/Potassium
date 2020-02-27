package posidon.potassium.backend

import posidon.potassium.Console
import posidon.potassium.backend.events.PlayerMovementEvent
import posidon.potassium.packets.PlayerJoinPacket
import posidon.potassium.tools.print
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.*

class Player(private val socket: Socket) : Thread(socket.inetAddress.hostAddress) {

    private lateinit var output: ObjectOutputStream
    private lateinit var input: ObjectInputStream
    var id = 0
    lateinit var playerName: String
    private var running = true
    var info: PlayerInfo? = null
    var x = 0f
    var y = 0f
    var z = 0f
    var moveSpeed = 0.5f
    var jumpHeight = 0.5f
    private var pendingMovementEvents: MutableList<PlayerMovementEvent> = ArrayList()

    fun addMovementEvent(movementEvent: PlayerMovementEvent) {
        pendingMovementEvents.add(movementEvent)
    }

    fun runMovementEvents() {
        val tmpPendingMovementEvents = pendingMovementEvents
        pendingMovementEvents = ArrayList()
        for (movEvent in tmpPendingMovementEvents) movEvent.run(this)
        if (tmpPendingMovementEvents.size > 10) Console.beforeCmdLine {
            Console.printProblem(playerName, " is sending too many packets!")
        }
        tmpPendingMovementEvents.clear()
    }

    fun send(obj: Any?) {
        try {
            output.writeObject(obj)
            output.flush()
        } catch (e: Exception) { e.print() }
    }

    override fun run() {
        while (running) {
            val obj = sent
            if (obj == null) disconnect() else ReceivedPacketHandler.onEvent(obj, id)
        }
    }

    private val sent: Any?
        get() {
            try { return input.readObject() }
            catch (ignore: Exception) {}
            return null
        }

    fun disconnect() {
        running = false
        try {
            output.close()
            input.close()
            socket.close()
        } catch (ignore: Exception) {}
        Console.beforeCmdLine {
            Console.printInfo(playerName, " left")
        }
        Players.remove(id)
        pendingMovementEvents.clear()
    }

    init {
        try {
            output = ObjectOutputStream(socket.getOutputStream())
            input = ObjectInputStream(socket.getInputStream())
            var tmp: Any? = null
            do try { tmp = input.readObject() }
            catch (e: ClassNotFoundException) {
                Console.beforeCmdLine {
                    Console.printProblem(socket.inetAddress.hostAddress, " sent an unknown class")
                }
            } while (tmp !is PlayerJoinPacket)
            val packet = tmp
            id = packet.id
            this.playerName = packet.name
            packet.player = this
            ReceivedPacketHandler.onEvent(packet, packet.id)
        } catch (e: IOException) { e.print() }
    }
}