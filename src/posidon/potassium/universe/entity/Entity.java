package posidon.potassium.universe.entity;

abstract public class Entity {
    public float x, y, z;
    public float rotX, rotY, rotZ;

    public Entity() {

    }

    public abstract void tick();
}
