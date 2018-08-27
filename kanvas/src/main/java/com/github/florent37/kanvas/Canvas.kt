package com.github.florent37.kanvas


import android.graphics.*
import com.github.florent37.kanvas.shape.Shape

//region android-ktx
//FROM Android-KTX https://github.com/android/android-ktx/

/**
 * Wrap the specified [block] in calls to [Canvas.save]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withSave(block: Canvas.() -> Unit) {
    val checkpoint = save()
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

/**
 * Wrap the specified [block] in calls to [Canvas.save]/[Canvas.translate]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withTranslation(
        x: Float = 0.0f,
        y: Float = 0.0f,
        block: Canvas.() -> Unit
) {
    val checkpoint = save()
    translate(x, y)
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

/**
 * Wrap the specified [block] in calls to [Canvas.save]/[Canvas.rotate]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withRotation(
        degrees: Float = 0.0f,
        pivotX: Float = 0.0f,
        pivotY: Float = 0.0f,
        block: Canvas.() -> Unit
) {
    val checkpoint = save()
    rotate(degrees, pivotX, pivotY)
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

/**
 * Wrap the specified [block] in calls to [Canvas.save]/[Canvas.scale]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withScale(
        x: Float = 1.0f,
        y: Float = 1.0f,
        pivotX: Float = 0.0f,
        pivotY: Float = 0.0f,
        block: Canvas.() -> Unit
) {
    val checkpoint = save()
    scale(x, y, pivotX, pivotY)
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

/**
 * Wrap the specified [block] in calls to [Canvas.save]/[Canvas.skew]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withSkew(
        x: Float = 0.0f,
        y: Float = 0.0f,
        block: Canvas.() -> Unit
) {
    val checkpoint = save()
    skew(x, y)
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

/**
 * Wrap the specified [block] in calls to [Canvas.save]/[Canvas.concat]
 * and [Canvas.restoreToCount].
 */
inline fun Canvas.withMatrix(
        matrix: Matrix = Matrix(),
        block: Canvas.() -> Unit
) {
    val checkpoint = save()
    concat(matrix)
    try {
        block()
    } finally {
        restoreToCount(checkpoint)
    }
}

//endregion

val canvasTmpRect = RectF()

fun Canvas.drawArc(centerX: Float, centerY: Float, circleRadius: Float, startAngle: Float, sweepAngle: Float, paint: Paint) {
    synchronized(canvasTmpRect) {
        canvasTmpRect.set(centerX - circleRadius, centerY - circleRadius, centerX + circleRadius, centerY + circleRadius)
        this.drawArc(canvasTmpRect, startAngle, sweepAngle, false, paint)
    }
}

fun Canvas.drawArc(center: PointF, circleRadius: Float, startAngle: Float, sweepAngle: Float, paint: Paint) {
    drawArc(center.x, center.y, circleRadius, startAngle, sweepAngle, paint)
}
fun Canvas.drawArc(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, useCenter: Boolean, paint: Paint) {
    synchronized(canvasTmpRect) {
        canvasTmpRect.set(left, top, right, bottom)
        drawArc(canvasTmpRect, startAngle, sweepAngle, useCenter, paint)
    }
}

fun Canvas.drawOval(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, useCenter: Boolean, paint: Paint) {
    synchronized(canvasTmpRect) {
        canvasTmpRect.set(left, top, right, bottom)
        drawOval(canvasTmpRect, paint)
    }
}

fun Canvas.drawLine(start: PointF, end: PointF, paint: Paint) {
    this.drawLine(start.x, start.y, end.x, end.y, paint)
}

fun Canvas.drawRoundRect(left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float, paint: Paint) {
    synchronized(canvasTmpRect) {
        canvasTmpRect.set(left, top, right, bottom)
        this.drawRoundRect(canvasTmpRect, rx, ry, paint)
    }
}

fun Canvas.draw(shape: Shape) {
    shape.onDraw(this)
}