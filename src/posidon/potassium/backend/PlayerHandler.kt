package posidon.potassium.backend

import java.util.*

object PlayerHandler {
    var players = HashMap<Int, Player>()
    var ids = HashMap<String?, Int>()
    fun getName(id: Int): String? {
        return players[id]!!.playerName
    }

    operator fun get(id: Int?): Player? {
        return players[id]
    }

    operator fun get(name: String?): Player? {
        return players[ids[name]]
    }

    fun put(id: Int, player: Player) {
        players[id] = player
        ids[player.playerName] = id
    }

    fun remove(id: Int) {
        players.remove(id)
    }

    fun remove(name: String?) {
        players.remove(ids[name])
    }

    val isEmpty: Boolean
        get() = players.isEmpty()

    fun keys(): Set<Int> {
        return players.keys
    }

    fun values(): Collection<Player> {
        return players.values
    }
}