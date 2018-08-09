package com.github.florent37.kanvas.value

import com.github.florent37.kanvas.shape.Shape

class Scale(private val shape: Shape) {
    var scaleX = 0f
    var scaleY = 0f
    val pivot = ScalePivot(shape)

    fun set(value : Number){
        scaleX = value.toFloat()
        scaleY = value.toFloat()
    }

    fun set(x : Number, y: Number){
        scaleX = x.toFloat()
        scaleY = y.toFloat()
    }
}