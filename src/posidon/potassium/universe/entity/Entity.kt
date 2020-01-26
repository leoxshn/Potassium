package posidon.potassium.universe.entity

abstract class Entity {
    var x = 0f
    var y = 0f
    var z = 0f
    var rotX = 0f
    var rotY = 0f
    var rotZ = 0f
    abstract fun tick()
}