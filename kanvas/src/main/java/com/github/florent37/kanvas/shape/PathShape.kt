package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF

open class PathShape : Shape() {

    var path = Path()
        set(value) {
            field.set(path)
        }

    val pathBounds = RectF()

    override val left: Float
        get() = pathBounds.left

    override val top: Float
        get() = pathBounds.top

    override val bottom: Float
        get() = pathBounds.bottom

    override val right: Float
        get() = pathBounds.right

    override val height: Float
        get() = pathBounds.height()

    override val width: Float
        get() = pathBounds.width()

    override val centerX: Float
        get() = pathBounds.centerX().toInt().toFloat()

    override val centerY: Float
        get() = pathBounds.centerY().toInt().toFloat()

    override fun update() {
        super.update()
        path.computeBounds(pathBounds, false)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun containsTouch(x: Float, y: Float): Boolean {
        return false
    }
}
