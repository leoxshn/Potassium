package posidon.potassium.backend

import posidon.potassium.Window
import posidon.potassium.backend.events.PlayerMovementEvent
import posidon.potassium.packets.PlayerJoinPacket
import posidon.potassium.ui.Color
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.util.*

class Player(private val socket: Socket) : Thread(socket.inetAddress.hostAddress) {
    private var output: ObjectOutputStream? = null
    private var input: ObjectInputStream? = null
    var id = 0
    @JvmField
    var playerName: String? = null
    private var running = true
    var info: PlayerInfo? = null
    @JvmField
    var x = 0f
    @JvmField
    var y = 0f
    @JvmField
    var z = 0f
    @JvmField
    var moveSpeed = 0.5f
    @JvmField
    var jumpHeight = 0.5f
    private var pendingMovementEvents: MutableList<PlayerMovementEvent> = ArrayList()
    fun addMovementEvent(movementEvent: PlayerMovementEvent) {
        pendingMovementEvents.add(movementEvent)
    }

    fun runMovementEvents() {
        val tmpPendingMovementEvents = pendingMovementEvents
        pendingMovementEvents = ArrayList()
        for (movEvent in tmpPendingMovementEvents) movEvent.run(this)
        if (tmpPendingMovementEvents.size > 10) Window.println("$playerName is sending too many packets!", Color.RED)
        tmpPendingMovementEvents.clear()
    }

    fun send(obj: Any?) {
        try {
            output!!.writeObject(obj)
            output!!.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun run() {
        while (running) {
            val obj = sent
            if (obj == null) disconnect() else EventListener.onEvent(obj, id)
        }
    }

    //catch (IOException e) { Window.println(name + "'s connection was lost"); }
    private val sent: Any?
        private get() {
            try {
                return input!!.readObject()
            } catch (e: ClassNotFoundException) {
                Window.println("$playerName sent an unknown packet!", Color.RED)
            } //catch (IOException e) { Window.println(name + "'s connection was lost"); }
            catch (ignore: Exception) {
            }
            return null
        }

    fun disconnect() {
        running = false
        try {
            output!!.close()
            input!!.close()
            socket.close()
        } catch (ignore: Exception) {}
        Window.print(playerName, Color.YELLOW)
        Window.println(" left", Color.GRAY)
        PlayerHandler.remove(id)
        pendingMovementEvents.clear()
    }

    init {
        try {
            output = ObjectOutputStream(socket.getOutputStream())
            input = ObjectInputStream(socket.getInputStream())
            var tmp: Any? = null
            do try {
                tmp = input!!.readObject()
            } catch (e: ClassNotFoundException) {
                Window.println(socket.inetAddress.hostAddress + " sent an unknown class")
            } while (tmp !is PlayerJoinPacket)
            val packet = tmp
            id = packet.id
            this.playerName = packet.name
            packet.player = this
            EventListener.onEvent(packet, packet.id)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}