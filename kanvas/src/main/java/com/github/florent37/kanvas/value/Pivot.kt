package com.github.florent37.kanvas.value

import com.github.florent37.kanvas.shape.Shape

abstract class Pivot(private val shape: Shape) {
    var x = 0
        set(value) {
            field = value
            setted = true
        }
    var y = 0
        set(value) {
            field = value
            setted = true
        }
    private var setted = false

    fun set(x: Number, y: Number) {
        this.x = x.toInt()
        this.y = y.toInt()
    }
}

class RotationPivot(shape: Shape) : Pivot(shape)

class ScalePivot(shape: Shape) : Pivot(shape)