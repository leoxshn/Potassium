package posidon.potassium.backend.events

import posidon.potassium.backend.Player

abstract class PlayerEvent {
    abstract fun run(player: Player)
}