package com.github.florent37.kanvas.value

import android.graphics.Paint
import android.graphics.PathEffect
import com.github.florent37.kanvas.shape.Shape

class Stroke (private val shape: Shape){
    var cap : Paint.Cap
        get() = shape.paint.strokeCap
        set(value) {
            shape.paint.strokeCap = value
            shape.update()
        }

    var width : Float
        get() = shape.paint.strokeWidth
        set(value) {
            shape.paint.strokeWidth = value
            shape.update()
        }

    var pathEffect : PathEffect
        get() = shape.paint.pathEffect
        set(value) {
            shape.paint.pathEffect = value
            shape.update()
        }
}