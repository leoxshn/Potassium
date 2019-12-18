package posidon.potassium.backend;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public float x, y, z;

    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float[] getPosition() {
        return new float[]{x, y, z};
    }
}
