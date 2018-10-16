package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import com.github.florent37.kanvas.anim.ArcShapeAnimation

open class ArcShape : PathShape {

    constructor() : super()

    constructor(block: (ArcShape.() -> Unit)) : super() {
        block.invoke(this)
    }

    constructor(v: View, block: (ArcShape.(view: View) -> Unit)) : super() {
        this.initWhenViewHasSize(v, block = block)
    }

    protected val animation = ArcShapeAnimation(this)

    protected val rectF = RectF()

    var startAngle = 0f
        set(value) {
            field = value
            update()
        }
    var sweepAngle = 0f
        set(value) {
            field = value
            update()
        }

    var radius = 0f
        set(value) {
            val centerX = centerX
            val centerY = centerY
            rectF.left = centerX - radius
            rectF.right = centerX + radius
            rectF.top = centerY - radius
            rectF.bottom = centerY + radius

            field = value
        }

    protected var _centerX = 0f
    override var centerX: Float
        set(value) {
            _centerX = value
            update()
        }
        get() = _centerX

    protected var _centerY = 0f
    override var centerY: Float
        set(value) {
            _centerY = value
            update()
        }
        get() = _centerY

    override fun update() {
        super.update()

        path.apply {
            reset()
            addArc(RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius), startAngle, sweepAngle)
        }
    }

    override fun draw(canvas: Canvas) {
        val save = canvas.save()

        canvas.translate(rectF.left, rectF.top)
        paint.style = Paint.Style.STROKE
        canvas.drawPath(super.path, paint)

        canvas.restoreToCount(save)
    }

    fun animate() = animation
}
