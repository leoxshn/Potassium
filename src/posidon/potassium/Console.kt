package posidon.potassium

import posidon.potassium.backend.Players
import posidon.potassium.tools.loop
import java.io.File

class Console : Runnable {

    companion object {
        val colors = if (File.listRoots().size == 1 && File.listRoots()[0].path == "/") UnixConsoleColors() else NoConsoleColors()
        val errHighlightColor = colors.YELLOW_BOLD_BRIGHT
        val errColor = colors.RED
        const val prefix = "> "

        inline fun println(string: String) = kotlin.io.println(string + colors.RESET)
        inline fun print(string: String) = kotlin.io.print(string + colors.RESET)
        inline fun printProblem(highlight: String, otherText: String) = println(errHighlightColor + highlight + errColor + otherText)
        inline fun printInfo(highlight: String, otherText: String) = println(colors.CYAN_BOLD_BRIGHT + highlight + colors.BLUE + otherText)

        inline fun backspace(characters: Int) {
            for (i in 0 until characters) print('\b')
        }

        inline fun beforeCmdLine(stuff: () -> Unit) {
            backspace(prefix.length)
            stuff()
            print(colors.BLUE_BOLD + prefix)
        }
    }

    override fun run() {
        loop {
            print(colors.BLUE_BOLD + prefix)
            val cmd = readLine()!!.split(' ')
            when (cmd[0]) {
                "", " " -> {}
                "stop" -> stop()
                "ip" -> {
                    Thread(Runnable {
                        val ip = Main.extIP
                        beforeCmdLine {
                            println(ip?.let { colors.PURPLE_BOLD + "ip" + colors.PURPLE + " -> " + colors.RESET + it } ?: colors.RED + "error: couldn't get external ip")
                        }
                    }).start()
                }
                "kick" -> {
                    if (cmd.size == 1) println(errColor + "kick who?")
                    else for (i in 1 until cmd.size) {
                        Players[cmd[i]]?.disconnect()
                            ?: println(errColor + "there's no player called " + errHighlightColor + cmd[i] + errColor + " on the server")
                    }
                }
                "players" -> {
                    if (Players.isEmpty) println("There are no players online")
                    else for (player in Players) println(player.playerName)
                }
                else -> printProblem(cmd[0], " isn't a valid command!")
            }
        }
    }

    abstract class ConsoleColors {
        abstract val RESET: String

        abstract val RED: String
        abstract val GREEN: String
        abstract val YELLOW: String
        abstract val BLUE: String
        abstract val PURPLE: String
        abstract val CYAN: String
        abstract val RED_BRIGHT: String
        abstract val GREEN_BRIGHT: String
        abstract val YELLOW_BRIGHT: String
        abstract val BLUE_BRIGHT: String
        abstract val PURPLE_BRIGHT: String
        abstract val CYAN_BRIGHT: String

        abstract val RED_BOLD: String
        abstract val GREEN_BOLD: String
        abstract val YELLOW_BOLD: String
        abstract val BLUE_BOLD: String
        abstract val PURPLE_BOLD: String
        abstract val CYAN_BOLD: String
        abstract val RED_BOLD_BRIGHT: String
        abstract val GREEN_BOLD_BRIGHT: String
        abstract val YELLOW_BOLD_BRIGHT: String
        abstract val BLUE_BOLD_BRIGHT: String
        abstract val PURPLE_BOLD_BRIGHT: String
        abstract val CYAN_BOLD_BRIGHT: String
    }

    class UnixConsoleColors : ConsoleColors() {
        override val RESET = "\u001b[0m"

        override val RED = "\u001b[0;31m"
        override val GREEN = "\u001b[0;32m"
        override val YELLOW = "\u001b[0;33m"
        override val BLUE = "\u001b[0;34m"
        override val PURPLE = "\u001b[0;35m"
        override val CYAN = "\u001b[0;36m"
        override val RED_BRIGHT = "\u001b[0;91m"
        override val GREEN_BRIGHT = "\u001b[0;92m"
        override val YELLOW_BRIGHT = "\u001b[0;93m"
        override val BLUE_BRIGHT = "\u001b[0;94m"
        override val PURPLE_BRIGHT = "\u001b[0;95m"
        override val CYAN_BRIGHT = "\u001b[0;96m"

        override val RED_BOLD = "\u001b[1;31m"
        override val GREEN_BOLD = "\u001b[1;32m"
        override val YELLOW_BOLD = "\u001b[1;33m"
        override val BLUE_BOLD = "\u001b[1;34m"
        override val PURPLE_BOLD = "\u001b[1;35m"
        override val CYAN_BOLD = "\u001b[1;36m"
        override val RED_BOLD_BRIGHT = "\u001b[1;91m"
        override val GREEN_BOLD_BRIGHT = "\u001b[1;92m"
        override val YELLOW_BOLD_BRIGHT = "\u001b[1;93m"
        override val BLUE_BOLD_BRIGHT = "\u001b[1;94m"
        override val PURPLE_BOLD_BRIGHT = "\u001b[1;95m"
        override val CYAN_BOLD_BRIGHT = "\u001b[1;96m"
    }

    class NoConsoleColors : ConsoleColors() {
        override val RESET = ""

        override val RED = ""
        override val GREEN = ""
        override val YELLOW = ""
        override val BLUE = ""
        override val PURPLE = ""
        override val CYAN = ""
        override val RED_BRIGHT = ""
        override val GREEN_BRIGHT = ""
        override val YELLOW_BRIGHT = ""
        override val BLUE_BRIGHT = ""
        override val PURPLE_BRIGHT = ""
        override val CYAN_BRIGHT = ""

        override val RED_BOLD = ""
        override val GREEN_BOLD = ""
        override val YELLOW_BOLD = ""
        override val BLUE_BOLD = ""
        override val PURPLE_BOLD = ""
        override val CYAN_BOLD = ""
        override val RED_BOLD_BRIGHT = ""
        override val GREEN_BOLD_BRIGHT = ""
        override val YELLOW_BOLD_BRIGHT = ""
        override val BLUE_BOLD_BRIGHT = ""
        override val PURPLE_BOLD_BRIGHT = ""
        override val CYAN_BOLD_BRIGHT = ""
    }
}