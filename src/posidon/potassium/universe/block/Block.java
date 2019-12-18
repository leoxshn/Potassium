package posidon.potassium.universe.block;

import java.io.Serializable;

public class Block implements Serializable {
    private static final long serialVersionUID = 1;
    public final String name;
    public final float hardness;
    public final float emission;

    public Block(String name, float hardness, float emission) {
        this.name = name;
        this.hardness = hardness;
        this.emission = emission;
    }

    public void tick() {

    }

    public static Block wood() { return new Block("wood", 0.5f, 0); }
    public static Block stone() { return new Block("stone", 1f, 0); }
    public static Block grass() { return new Block("grass", 0.1f, 0); }
}
