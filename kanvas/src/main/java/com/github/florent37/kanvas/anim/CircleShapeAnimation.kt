package com.github.florent37.kanvas.anim

import com.github.florent37.kanvas.anim.core.ColorValueAnimation
import com.github.florent37.kanvas.anim.core.FloatValueAnimation
import com.github.florent37.kanvas.anim.core.ShapeAnimation
import com.github.florent37.kanvas.shape.CircleShape

open class CircleShapeAnimation(shape: CircleShape) : ShapeAnimation<CircleShape>(shape) {
    val centerX = CenterX(this)
    val centerY = CenterY(this)
    val radius = Radius(this)
    val border = Border(this)


    class CenterX(anim: CircleShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerX
            }
            setValue = {
                anim.shape.centerX = it
            }
        }
    }

    class CenterY(anim: CircleShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerY
            }
            setValue = {
                anim.shape.centerY = it
            }
        }
    }

    class Radius(anim: CircleShapeAnimation) : FloatValueAnimation() {

        init {
            getValue = {
                anim.shape.radius
            }
            setValue = {
                anim.shape.radius = it
            }
        }
    }

    class Border(val anim: CircleShapeAnimation) {
        val color = BorderColor(this)
        val width = BorderWidth(this)
    }

    class BorderColor(val border: Border) : ColorValueAnimation() {
        init {
            getValue = {
                border.anim.shape.border.color
            }
            setValue = {
                border.anim.shape.border.color = it
            }
        }
    }

    class BorderWidth(val border: Border) : FloatValueAnimation() {
        init {
            getValue = {
                border.anim.shape.border.width
            }
            setValue = {
                border.anim.shape.border.width = it
            }
        }
    }
}