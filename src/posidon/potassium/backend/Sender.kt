package posidon.potassium.backend

object Sender {
    fun sendToAllPlayers(obj: Any?) {
        for (player in PlayerHandler.values()) player!!.send(obj)
    }
}