package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.PathEffect
import android.support.annotation.ColorInt

open class LineShape : Shape() {

    var startX = 0.0f
    var startY = 0.0f
    var endX = 0.0f
    var endY = 0.0f

    override val centerX: Float
        get() = (startX + endX) / 2f

    override val centerY: Float
        get() = (endX + endY) / 2f

    override val left: Float
        get() = Math.min(startX, endX)

    override val top: Float
        get() = Math.min(startY, endY)

    override val bottom: Float
        get() = Math.max(startY, endY)

    override val right: Float
        get() = Math.max(startX, endX)

    override val height: Float
        get() = Math.max(startY, endY) - Math.min(startY, endY)

    override val width: Float
        get() = Math.max(startX, endX) - Math.min(startX, endX)

    override fun draw(canvas: Canvas) {
        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    override fun containsTouch(x: Float, y: Float): Boolean {
        return false
    }

    fun start(x: Float, y: Float): LineShape {
        startX = x
        startY = y
        return this
    }

    fun end(x: Float, y: Float): LineShape {
        endX = x
        endY = y
        return this
    }
}
