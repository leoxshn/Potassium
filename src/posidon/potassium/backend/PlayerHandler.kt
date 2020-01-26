package posidon.potassium.backend;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class PlayerHandler {
    public static HashMap<Integer, Player> players = new HashMap<>();
    public static HashMap<String, Integer> ids = new HashMap<>();

    public static String getName(int id) { return players.get(id).name; }
    public static Player get(Integer id) { return players.get(id); }
    public static Player get(String name) { return players.get(ids.get(name)); }

    public static void put(int id, Player player) {
        players.put(id, player);
        ids.put(player.name, id);
    }
    public static void remove(int id) { players.remove(id); }
    public static void remove(Player player) { players.remove(player); }
    public static void remove(String name) { players.remove(ids.get(name)); }

    public static boolean isEmpty() { return players.isEmpty(); }
    public static Set<Integer> keys() { return players.keySet(); }
    public static Collection<Player> values() { return players.values(); }
}
