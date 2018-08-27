package com.github.florent37.kanvas

import android.graphics.*

val pathTmpRect = RectF()

fun Path.addRoundRect(left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float, dir: Path.Direction) {
    synchronized(pathTmpRect) {
        pathTmpRect.set(left, top, right, bottom)
        this.addRoundRect(pathTmpRect, rx, ry, dir)
    }
}

fun Path.addRoundRect(left: Float, top: Float, right: Float, bottom: Float, radii: FloatArray, dir: Path.Direction) {
    synchronized(pathTmpRect) {
        pathTmpRect.set(left, top, right, bottom)
        this.addRoundRect(pathTmpRect, radii, dir)
    }
}

fun Path.arcTo(centerX: Float, centerY: Float, circleRadius: Float, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean) {
    synchronized(pathTmpRect) {
        pathTmpRect.set(centerX - circleRadius, centerY - circleRadius, centerX + circleRadius, centerY + circleRadius)
        arcTo(pathTmpRect, startAngle, sweepAngle, forceMoveTo)
    }
}

fun Path.arcTo(center: PointF, circleRadius: Float, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean) {
    arcTo(center.x, center.y, circleRadius, startAngle, sweepAngle, forceMoveTo)
}

fun Path.arcTo(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean) {
    synchronized(pathTmpRect) {
        pathTmpRect.set(left, top, right, bottom)
        arcTo(pathTmpRect, startAngle, sweepAngle, forceMoveTo)
    }
}
