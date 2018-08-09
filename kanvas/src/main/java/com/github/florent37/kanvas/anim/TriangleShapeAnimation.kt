package com.github.florent37.kanvas.anim

import com.github.florent37.kanvas.anim.core.FloatValueAnimation
import com.github.florent37.kanvas.anim.core.ShapeAnimation
import com.github.florent37.kanvas.shape.TriangleShape

class TriangleShapeAnimation(shape: TriangleShape) : ShapeAnimation<TriangleShape>(shape) {

    class CenterX(anim: TriangleShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerX
            }
            setValue = {
                anim.shape.centerX = it
            }
        }
    }

    class CenterY(anim: TriangleShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerY
            }
            setValue = {
                anim.shape.centerY = it
            }
        }
    }

    val centerX = CenterX(this)
    val centerY = CenterY(this)

}
