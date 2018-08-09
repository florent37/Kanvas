package com.github.florent37.kanvas.anim

import android.animation.ValueAnimator
import com.github.florent37.kanvas.anim.core.ColorValueAnimation
import com.github.florent37.kanvas.anim.core.FloatValueAnimation
import com.github.florent37.kanvas.anim.core.ShapeAnimation
import com.github.florent37.kanvas.anim.core.insertAtFirst
import com.github.florent37.kanvas.shape.RectShape

open class RectShapeAnimation<out S : RectShape>(shape: S) : ShapeAnimation<S>(shape) {

    val left = Left(this)
    val right = Right(this)
    val top = Top(this)
    val bottom = Bottom(this)

    val border = Border(this)

    val centerX = CenterX(this)
    val centerY = CenterY(this)

    // Move the rect to the left, keeping his width
    /*
    fun moveLeftTo(vararg values: Float): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.left, values))
        valueAnimator.addUpdateListener { animation -> shape.moveLeftTo(animation.animatedValue as Float) }
        return valueAnimator
    }

    // Move the rect to the right, keeping his height
    fun moveRightTo(vararg values: Float): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.right, values))
        valueAnimator.addUpdateListener { animation -> shape.moveRightTo(animation.animatedValue as Float) }
        return valueAnimator
    }

    // Move the rect to the top, keeping his height
    fun moveTopTo(vararg values: Float): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.top, values))
        valueAnimator.addUpdateListener { animation -> shape.moveTopTo(animation.animatedValue as Float) }
        return valueAnimator
    }


    // Move the rect to the bottom, keeping his height
    fun moveBottomTo(vararg values: Float): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(insertAtFirst(shape.bottom, values))
        valueAnimator.addUpdateListener { animation -> shape.moveBottomTo(animation.animatedValue as Float) }
        return valueAnimator
    }
    */

    //endregion

    class CenterX(anim: RectShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerX
            }
            setValue = {
                anim.shape.centerX = it
            }
        }
    }

    class CenterY(anim: RectShapeAnimation<*>) : FloatValueAnimation() {
        init {
            getValue = {
                anim.shape.centerY
            }
            setValue = {
                anim.shape.centerY = it
            }
        }
    }


    class Left(anim: RectShapeAnimation<*>) : FloatValueAnimation(){
        init {
            getValue = {
                anim.shape.left
            }
            setValue = {
                anim.shape.left = it
            }
        }
    }

    class Top(anim: RectShapeAnimation<*>) : FloatValueAnimation(){
        init {
            getValue = {
                anim.shape.top
            }
            setValue = {
                anim.shape.top = it
            }
        }
    }

    class Right(anim: RectShapeAnimation<*>) : FloatValueAnimation(){
        init {
            getValue = {
                anim.shape.right
            }
            setValue = {
                anim.shape.right = it
            }
        }
    }

    class Bottom(anim: RectShapeAnimation<*>) : FloatValueAnimation(){
        init {
            getValue = {
                anim.shape.bottom
            }
            setValue = {
                anim.shape.bottom = it
            }
        }
    }

    class Border(val anim: RectShapeAnimation<*>) {
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
