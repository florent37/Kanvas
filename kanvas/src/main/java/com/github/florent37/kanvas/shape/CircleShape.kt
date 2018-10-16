package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.view.View
import com.github.florent37.kanvas.anim.ArcShapeAnimation
import com.github.florent37.kanvas.anim.CircleShapeAnimation
import com.github.florent37.kanvas.value.Border

open class CircleShape : Shape {

    protected val animation = CircleShapeAnimation(this)

    constructor() : super()

    constructor(block: (CircleShape.() -> Unit)) : super() {
        block.invoke(this)
    }

    constructor(v: View, block: (CircleShape.(view: View) -> Unit)) : super() {
        this.initWhenViewHasSize(v, block = block)
    }

    var radius = 0f
    override var centerX = 0f
        set(value) {
            field = limitX(centerX)
        }
    override var centerY = 0f
        set(value) {
            field = limitX(centerY)
        }

    val border = Border(this)

    override val left: Float
        get() = centerX - radius

    override val top: Float
        get() = centerY - radius

    override val bottom: Float
        get() = centerY + radius

    override val right: Float
        get() = centerX + radius

    override val height: Float
        get() = radius * 2f

    override val width: Float
        get() = radius * 2f

    fun centerVertical(parentHeight: Float): CircleShape {
        this.centerY = parentHeight / 2f
        return this
    }

    fun centerHorizontal(parentWidth: Float): CircleShape {
        this.centerX = parentWidth / 2f
        return this
    }

    private fun limitX(value: Float): Float {
        var value = value
        val left = value - radius
        val right = value + radius

        if (left < minX) {
            value = minX + radius
        }
        if (right > maxX) {
            value = maxX - radius
        }
        return value
    }

    private fun limitY(value: Float): Float {
        var value = value
        val top = value - radius
        val bottom = value + radius
        if (top < minY) {
            value = minY + radius
        }
        if (bottom > maxY) {
            value = maxY - radius
        }
        return value
    }

    override fun draw(canvas: Canvas) {
        if (border.width > 0) {
            val color = paint.color
            paint.color = border.color
            canvas.drawCircle(centerX, centerY, radius, paint)
            paint.color = color
        }
        canvas.drawCircle(centerX, centerY, radius - border.width, paint)
    }

    override fun containsTouch(x: Float, y: Float): Boolean {
        return Math.pow((x - centerX).toDouble(), 2.0) + Math.pow((y - centerY).toDouble(), 2.0) < Math.pow(radius.toDouble(), 2.0)
    }

    enum class Pos {
        CENTER_X,
        CENTER_Y
    }

    fun animate() = animation
}
