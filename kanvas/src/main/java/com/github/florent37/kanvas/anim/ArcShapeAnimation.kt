package com.github.florent37.kanvas.anim

import com.github.florent37.kanvas.anim.core.FloatValueAnimation
import com.github.florent37.kanvas.anim.core.ShapeAnimation
import com.github.florent37.kanvas.shape.ArcShape

open class ArcShapeAnimation(shape: ArcShape) : ShapeAnimation<ArcShape>(shape) {
    val centerX = StartAngle(this)
    val centerY = StartAngle(this)
    val startAngle = StartAngle(this)
    val sweepAngle = SweepAngle(this)

    class StartAngle(anim: ArcShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.startAngle
            }
            setValue = {
                anim.shape.startAngle = it
            }
        }
    }

    class SweepAngle(anim: ArcShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.sweepAngle
            }
            setValue = {
                anim.shape.sweepAngle = it
            }
        }
    }

    class CenterX(anim: ArcShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerX
            }
            setValue = {
                anim.shape.centerX = it
            }
        }
    }

    class CenterY(anim: ArcShapeAnimation) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerY
            }
            setValue = {
                anim.shape.centerY = it
            }
        }
    }
}
