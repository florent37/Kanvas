package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.support.annotation.CallSuper
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.text.TextPaint
import com.github.florent37.kanvas.value.Rotation
import com.github.florent37.kanvas.value.Scale
import com.github.florent37.kanvas.value.Shadow
import com.github.florent37.kanvas.Variables
import com.github.florent37.kanvas.anim.core.ShapeAnimation
import com.github.florent37.kanvas.value.Stroke

abstract class Shape protected constructor() {

    val paint = TextPaint()

    var willNotDraw = false

    var rotation = Rotation(this)
    var scale = Scale(this)
    val shadow = Shadow(this)
    val stroke = Stroke(this)

    var minX = 0f
    var maxX = Float.MAX_VALUE
    var minY = 0f
    var maxY = Float.MAX_VALUE

    val variables = Variables()

    abstract val height: Float
    abstract val width: Float

    abstract val centerX: Float

    abstract val centerY: Float

    var alpha: Float
        get() = paint.alpha / 255f
        set(@FloatRange(from = 0.0, to = 1.0) value){
            paint.alpha = (255 * value).toInt()
            update()
        }

    var color: Int
        @ColorInt
        get() = paint.color
        set(@ColorInt value){
            paint.color = value
            update()
        }

    abstract val left: Float
    abstract val top: Float
    abstract val bottom: Float
    abstract val right: Float

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 1f
        paint.strokeCap = Paint.Cap.ROUND
        paint.typeface = Typeface.DEFAULT
    }

    var style : Paint.Style
        get() = paint.style
        set(value) {
            paint.style = value
            update()
        }


    protected abstract fun draw(canvas: Canvas)

    fun onDraw(canvas: Canvas) {
        if (!willNotDraw) {
            canvas.save()
            draw(canvas)
            canvas.restore()
        }
    }

    @CallSuper
    internal open fun update() {

    }

    abstract fun containsTouch(x: Float, y: Float): Boolean
}
