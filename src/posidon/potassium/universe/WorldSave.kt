package posidon.potassium.universe;

import posidon.potassium.backend.Player;

import java.io.Serializable;
import java.util.HashMap;

public class WorldSave implements Serializable {

    private static final long serialVersionUID = 1;
    public double time;

    // x, y, z, health, uranium
    public static HashMap<Integer, int[]> playerInfo = new HashMap<Integer, int[]>();

    public WorldSave() {
        this.time = Globals.getTime();
    }
}
