package posidon.potassium.backend

object Sender {
    fun sendToAllPlayers(obj: Any?) {
        for (player in Players) player.send(obj)
    }
}