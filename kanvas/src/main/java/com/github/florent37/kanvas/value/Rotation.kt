package com.github.florent37.kanvas.value

import com.github.florent37.kanvas.shape.Shape

class Rotation(private val shape: Shape) {
    var value = 0f
    val pivot = RotationPivot(shape)
}