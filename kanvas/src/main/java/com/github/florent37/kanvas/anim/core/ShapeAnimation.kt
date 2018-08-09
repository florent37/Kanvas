package com.github.florent37.kanvas.anim.core

import com.github.florent37.kanvas.shape.Shape

open class ShapeAnimation<out S : Shape>(internal val shape: S) {

    val alpha = Alpha(this)
    val color = Color(this)
    val rotation = Rotation(this)
    val scaleX = ScaleX(this)
    val scaleY = ScaleY(this)
    val strokeWidth = StrokeWidth(this)

    class Alpha(anim: ShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.alpha
            }
            setValue = {
                anim.shape.alpha = it
            }
        }
    }

    class StrokeWidth(anim: ShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.stroke.width
            }
            setValue = {
                anim.shape.stroke.width = it
            }
        }
    }

    class Rotation(anim: ShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.rotation.value
            }
            setValue = {
                anim.shape.rotation.value = it
            }
        }
    }

    class ScaleX(anim: ShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.scale.scaleX
            }
            setValue = {
                anim.shape.scale.scaleX = it
            }
        }
    }

    class ScaleY(anim: ShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.scale.scaleY
            }
            setValue = {
                anim.shape.scale.scaleY = it
            }
        }
    }

    class Color(anim: ShapeAnimation<*>) : ColorValueAnimation() {
        init {
            getValue = {
                anim.shape.color
            }
            setValue = {
                anim.shape.color = it
            }
        }
    }
}
