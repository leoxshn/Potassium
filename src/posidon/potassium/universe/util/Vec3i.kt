package posidon.potassium.universe.util;

import java.util.Objects;

public class Vec3i {
    int x, y, z;

    public Vec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3i sum(Vec3i a, Vec3i b) { return new Vec3i(a.x + b.x, a.y + b.y, a.z + b.z); }
    public static Vec3i subtract(Vec3i a, Vec3i b) { return new Vec3i(a.x - b.x, a.y - b.y, a.z - b.z); }
    public static Vec3i multiply(Vec3i a, Vec3i b) { return new Vec3i(a.x * b.x, a.y * b.y, a.z * b.z); }
    public static Vec3i multiply(Vec3i a, int b) { return new Vec3i(a.x * b, a.y * b, a.z * b); }
    public static Vec3i divide(Vec3i a, Vec3i b) { return new Vec3i(a.x / b.x, a.y / b.y, a.z / b.z); }
    public static Vec3i divide(Vec3i a, int b) { return new Vec3i(a.x / b, a.y / b, a.z / b); }
    public static int length(Vec3i v) { return (int)Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z); }
    public static Vec3i normalize(Vec3i v) { return Vec3i.divide(v, Vec3i.length(v)); }
    public static int dot(Vec3i a, Vec3i b) { return a.x * b.x + a.y * b.y + a.z * b.z; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec3i)) return false;
        Vec3i vec = (Vec3i) o;
        return vec.x == x && vec.y == y && vec.z == z;
    }
    @Override
    public int hashCode() { return Objects.hash(x, y, z); }
}
