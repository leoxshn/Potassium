package posidon.potassium.tools

import posidon.potassium.Console
import posidon.potassium.Main

object KotlinFixes {
    inline infix fun Byte.eq(int: Int) = this == int.toByte()
    inline infix fun Any.eq(any: Any) = this == any
    inline infix fun Any.neq(any: Any) = this == any
}

inline fun loop(methods: () -> Unit) { while (Main.running) methods() }
fun Throwable.print() = Console.beforeCmdLine {
    print(Console.colors.RED)
    printStackTrace()
    print(Console.colors.RESET)
}