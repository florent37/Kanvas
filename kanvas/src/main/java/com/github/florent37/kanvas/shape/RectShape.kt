package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.View
import com.github.florent37.kanvas.RoundRect
import com.github.florent37.kanvas.anim.RectShapeAnimation
import com.github.florent37.kanvas.value.Border

fun View.rectShape(block: (RectShape.(view: View) -> Unit)) : RectShape {
    return RectShape(this, block)
}

open class RectShape : PathShape {

    protected val animation = RectShapeAnimation(this)

    val border = Border(this)

    constructor() : super()

    constructor(block: (RectShape.() -> Unit)) : super() {
        block.invoke(this)
    }

    constructor(v: View, block: (RectShape.(view: View) -> Unit)) : super() {
        this.initWhenViewHasSize(v, block = block)
    }

    protected val rectF = RectF()

    override var bottom: Float
        get() = rectF.bottom
        set(value) {
            rectF.bottom = Math.min(value, maxY)
            update()
        }

    override var top: Float
        get() = rectF.top
        set(value) {
            rectF.top = Math.max(value, minY)
            update()
        }

    override var left: Float
        get() = rectF.left
        set(value) {
            rectF.left = Math.max(value, minX)
            update()
        }

    override var right: Float
        get() = rectF.right
        set(value) {
            rectF.right = Math.min(value, maxX)
            update()
        }

    override var width: Float
        get() = Math.max(0f, right - left)
        set(value) {
            right = (left + value)
            update()
        }

    override var height: Float
        get() = Math.max(0f, bottom - top)
        set(value) {
            bottom = (top + value)
            update()
        }


    var cornerRadius = 0f
        set(value) {
            field = value
            update()
        }

    var drawAngleTopLeft = true
        set(value) {
            field = value
            update()
        }
    var drawAngleTopRight = true
        set(value) {
            field = value
            update()
        }
    var drawAngleBottomLeft = true
        set(value) {
            field = value
            update()
        }
    var drawAngleBottomRight = true
        set(value) {
            field = value
            update()
        }

    override var centerX: Float
        get() = rectF.centerX()
        set(value) {
            this.moveXBy(value - centerX)
        }

    override var centerY: Float
        get() = rectF.centerY()
        set(value) {
            this.moveYBy(value - centerY)
        }


    override fun draw(canvas: Canvas) {
        val save = canvas.save()

        if (rotation.value != 0f) {
            canvas.rotate(rotation.value, rotation.pivot.x.toFloat(), rotation.pivot.y.toFloat())
        }
        canvas.translate(rectF.left, rectF.top)

        canvas.drawPath(super.path, paint)

        if (border.width != 0f) {
            val oldStyle = paint.style
            val strokeWidth = paint.strokeWidth
            val paintColor = paint.color
            paint.style = Paint.Style.STROKE
            paint.color = border.color
            paint.strokeWidth = border.width

            canvas.drawPath(super.path, paint)

            paint.color = paintColor
            paint.strokeWidth = strokeWidth
            paint.style = oldStyle
        }

        canvas.restoreToCount(save)
    }

    override fun update() {
        super.update()
        if (cornerRadius == 0f) {
            path.apply {
                reset()
                moveTo(0f, 0f)
                addRect(0f, 0f, width, height, Path.Direction.CW)
                close()
            }
        } else {
            path.apply {
                reset()
                set(RoundRect.generatePath(
                        width, height,
                        if (drawAngleTopLeft) cornerRadius else 0f,
                        if (drawAngleTopRight) cornerRadius else 0f,
                        if (drawAngleBottomRight) cornerRadius else 0f,
                        if (drawAngleBottomLeft) cornerRadius else 0f
                ))
            }
        }
    }

    fun setRect(left: Float, top: Float, right: Float, bottom: Float) {
        this.rectF.set(
                Math.max(left, minX),
                Math.max(top, minY),
                Math.min(right, maxX),
                Math.min(bottom, maxY))
        update()
    }

    fun setRect(rectF: RectF) {
        this.setRect(
                rectF.left,
                rectF.top,
                rectF.right,
                rectF.bottom
        )
    }

    fun copyPosition(other: RectShape) {
        this.rectF.set(other.rectF)
    }

    fun addLeft(add: Float): RectShape {
        left = (left + add)
        return this
    }

    fun addRight(add: Float): RectShape {
        right = (right + add)
        return this
    }

    fun addTop(add: Float): RectShape {
        top = (top + add)
        return this
    }

    fun addBottom(add: Float): RectShape {
        bottom = (bottom + add)
        return this
    }

    fun marginTop(margin: Float): RectShape {
        return this.moveTopTo(top + margin)
    }

    fun marginLeft(margin: Float): RectShape {
        return this.moveLeftTo(left + margin)
    }

    fun marginRight(margin: Float): RectShape {
        return this.moveRightTo(right - margin)
    }

    fun marginBottom(margin: Float): RectShape {
        return this.moveBottomTo(bottom - margin)
    }

    fun below(other: RectShape): RectShape {
        //TODO check min/max
        val height = height
        top = (other.bottom)
        top = (top + height)
        return this
    }

    fun above(other: RectShape): RectShape {
        //TODO check min/max
        val height = height
        bottom = (other.top)
        top = (bottom - height)
        return this
    }

    fun centerHorizontal(parentWidth: Float): RectShape {
        val width = width
        val newLeft = parentWidth / 2f - width / 2f
        left = (newLeft)
        right = (left + width)
        return this
    }

    fun centerVertical(parentHeight: Float) {
        val height = height
        val newTop = parentHeight / 2f - height / 2f
        top = newTop
        bottom = (top + height)
    }

    fun alignTop(value: Float): RectShape {
        val height = height
        top = value
        bottom = (top + height)
        return this
    }

    fun alignTop(other: RectShape): RectShape {
        alignTop(other.top)
        return this
    }

    fun alignBottom(value: Float): RectShape {
        val height = height
        bottom = bottom
        top = (bottom - height)
        return this
    }

    fun alignBottom(other: RectShape): RectShape {
        alignBottom(other.bottom)
        return this
    }

    fun moveXBy(differenceX: Float): RectShape {
        val width = width
        val oldLeft = left
        val oldRight = right

        val newLeft: Float
        val newRight: Float

        if (oldLeft + differenceX < minX) {
            newLeft = minX
            newRight = minX + width
        } else if (oldRight + differenceX > maxX) {
            newLeft = maxX - width
            newRight = maxX
        } else {
            newLeft = oldLeft + differenceX
            newRight = oldRight + differenceX
        }

        left = newLeft
        right = newRight
        return this
    }

    fun moveLeftTo(newLeft: Float): RectShape {
        return this.moveXBy(newLeft - left)
    }

    fun toRightOf(other: RectShape): RectShape {
        return moveLeftTo(other.right)
    }

    fun toLeftOf(other: RectShape): RectShape {
        return moveRightTo(other.left)
    }

    fun moveRightTo(newRight: Float): RectShape {
        return this.moveXBy(newRight - right)
    }

    fun moveTopTo(newTop: Float): RectShape {
        return this.moveYBy(newTop - top)
    }

    fun moveBottomTo(newBottom: Float): RectShape {
        return this.moveYBy(newBottom - bottom)
    }

    fun moveYBy(differenceY: Float): RectShape {
        val height = height
        val oldTop = top
        val oldBottom = bottom

        val newTop: Float
        val newBottom: Float

        if (oldTop + differenceY < minY) {
            newTop = minY
            newBottom = minY + height
        } else if (oldBottom + differenceY > maxY) {
            newTop = maxY - height
            newBottom = maxY
        } else {
            newTop = oldTop + differenceY
            newBottom = oldBottom + differenceY
        }

        top = newTop
        bottom = newBottom
        return this
    }

    fun moveBy(differenceX: Float, differenceY: Float): RectShape {
        moveXBy(differenceX)
        moveYBy(differenceY)
        return this
    }

    override fun containsTouch(x: Float, y: Float): Boolean {
        return rectF.contains(x, y)
    }

    enum class Pos {
        CENTER_X,
        CENTER_Y,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    fun animate() = animation
}
