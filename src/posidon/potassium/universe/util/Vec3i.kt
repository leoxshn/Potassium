package posidon.potassium.universe.util

import java.util.*

class Vec3i(var x: Int, var y: Int, var z: Int) {
    operator fun set(x: Int, y: Int, z: Int) {
        this.x = x
        this.y = y
        this.z = z
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Vec3i) return false
        val vec = o
        return vec.x == x && vec.y == y && vec.z == z
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y, z)
    }

    companion object {
        fun sum(a: Vec3i, b: Vec3i): Vec3i {
            return Vec3i(a.x + b.x, a.y + b.y, a.z + b.z)
        }

        fun subtract(a: Vec3i, b: Vec3i): Vec3i {
            return Vec3i(a.x - b.x, a.y - b.y, a.z - b.z)
        }

        fun multiply(a: Vec3i, b: Vec3i): Vec3i {
            return Vec3i(a.x * b.x, a.y * b.y, a.z * b.z)
        }

        fun multiply(a: Vec3i, b: Int): Vec3i {
            return Vec3i(a.x * b, a.y * b, a.z * b)
        }

        fun divide(a: Vec3i, b: Vec3i): Vec3i {
            return Vec3i(a.x / b.x, a.y / b.y, a.z / b.z)
        }

        fun divide(a: Vec3i, b: Int): Vec3i {
            return Vec3i(a.x / b, a.y / b, a.z / b)
        }

        fun length(v: Vec3i): Int {
            return Math.sqrt(v.x * v.x + v.y * v.y + (v.z * v.z).toDouble()).toInt()
        }

        fun normalize(v: Vec3i): Vec3i {
            return divide(v, length(v))
        }

        fun dot(a: Vec3i, b: Vec3i): Int {
            return a.x * b.x + a.y * b.y + a.z * b.z
        }
    }

}