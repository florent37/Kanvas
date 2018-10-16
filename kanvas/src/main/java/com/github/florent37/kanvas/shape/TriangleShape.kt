package com.github.florent37.kanvas.shape

import android.graphics.PointF
import android.view.View
import com.github.florent37.kanvas.anim.CircleShapeAnimation
import com.github.florent37.kanvas.anim.TriangleShapeAnimation

class TriangleShape : PathShape {

    constructor() : super()

    constructor(block: (TriangleShape.() -> Unit)) : super() {
        block.invoke(this)
    }

    constructor(v: View, block: (TriangleShape.(view: View) -> Unit)) : super() {
        this.initWhenViewHasSize(v, block = block)
    }

    protected val animation = TriangleShapeAnimation(this)

    val point0 = PointF(0f, 0f)
    val point1 = PointF(0f, 0f)
    val point2 = PointF(0f, 0f)

    override var centerX: Float = 0.0f
        get
        set(value) {
            this.moveXPlus(value - centerX)
        }

    override var centerY: Float = 0.0f
        get
        set(value) {
            this.moveXPlus(value - centerY)
        }


    private val points = listOf(point0, point1, point2)

    override fun update() {
        super.update()
        path.apply {
            reset()
            moveTo(point0.x, point0.y)
            lineTo(point1.x, point1.y)
            lineTo(point2.x, point2.y)
            close()
        }
    }

    fun setPoint0(x: Float, y: Float): TriangleShape {
        point0.set(x, y)
        update()
        return this
    }

    fun setPoint1(x: Float, y: Float): TriangleShape {
        point1.set(x, y)
        update()
        return this
    }

    fun setPoint2(x: Float, y: Float): TriangleShape {
        point2.set(x, y)
        update()
        return this
    }

    fun moveXPlus(differenceX: Float): TriangleShape {
        points.forEach { it.x += differenceX }
        update()
        return this
    }

    fun moveYPlus(differenceY: Float): TriangleShape {
        points.forEach { it.y += differenceY }
        update()
        return this
    }

    fun animate() = animation
}
