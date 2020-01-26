package posidon.potassium.backend.events;

import posidon.potassium.backend.Player;

abstract class PlayerEvent {
    abstract void run(Player player);
}