package posidon.potassium

import posidon.potassium.backend.PlayerHandler
import posidon.potassium.packets.Packet
import posidon.potassium.ui.Color
import posidon.potassium.universe.Globals

import java.util.Arrays
import kotlin.system.exitProcess

class Commands {

    private fun teleport(args: Array<String>?) {
        if (args!!.size == 4) {
            val tp = Packet()
            PlayerHandler[args[0]]!!.info!!.setPosition(args[1].toFloat(), args[2].toFloat(), args[3].toFloat())
            tp["position"] = PlayerHandler[args[0]]!!.info!!.position
            PlayerHandler[args[0]]!!.send(tp)
        }
    }

    private fun kill(args: Array<String>?) { /*TODO for(String name : args) kill(name)*/ }

    private fun playerList(args: Array<String>?) {
        if (PlayerHandler.isEmpty) Window.println("No players are connected right now")
        else {
            Window.println("Players:")
            for (id in PlayerHandler.keys())
                Window.println("  - " + PlayerHandler.getName(id))
        }
    }

    private fun print(args: Array<String>?) {
        if (args == null) return
        val txt = StringBuilder()
        for (word in args) txt.append(word).append(' ')
        Window.println(txt.toString())
    }

    private fun whisper(args: Array<String>) {
        val txt = StringBuilder()
        for (arg in args) txt.append(arg)
        /*TODO whisper(args[0], message)*/
    }

    private fun kick(args: Array<String>?) {
        for(name in args!!.iterator()) {
            PlayerHandler[name]!!.disconnect()
        }
    }

    private fun ban(args: Array<String>?) { /*TODO for(String name : args) ban(name)*/ }

    private fun time(args: Array<String>?) {
        if (args == null) Window.println("Time: " + Globals.time, Color.BLUE)
        else if (args.size == 2) {
            if (args[0] == "set") {
                Globals.time = args[1].toDouble()
                Window.println("Time set to " + Globals.time, Color.BLUE)
            } else if (args[0] == "add") {
                Globals.time += args[1].toDouble()
                Window.println("Time set to " + Globals.time, Color.BLUE)
            }
        }
    }

    fun onCommand(input: Array<out String>?) { try {
        val cmd = input!![0]
        val args = if (input.size > 1) Arrays.copyOfRange(input, 1, input.size) else null
        when (cmd) {
            "ban" -> ban(args)
            "clear" -> Window.clear()
            "ip" -> {
                val tmp = Main.extIP
                if (tmp.startsWith("error: ")) Window.println(tmp, Color.RED)
                else Window.println("IP: $tmp", Color.BLUE)
            }
            "kick" -> kick(args)
            "kill" -> kill(args)
            "playerlist" -> playerList(args)
            "playerList" -> playerList(args)
            "print" -> print(args)
            "stop" -> exitProcess(0)
            "time" -> time(args)
            "tp" -> teleport(args)
            "whisper" -> whisper(args!!)
            "msg" -> whisper(args!!)
            "message" -> whisper(args!!)
            else -> Window.println("$cmd isn't a valid command!", Color.PINK)
        }} catch (e: Exception) { Window.println("error: " + e.message, Color.RED) }
    }
}
