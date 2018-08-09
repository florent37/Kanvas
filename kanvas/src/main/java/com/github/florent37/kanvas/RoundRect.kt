package com.github.florent37.kanvas

import android.graphics.Path
import android.graphics.RectF

object RoundRect {

    fun generatePath(width: Float, height: Float, topLeftRadius: Float, topRightRadius: Float, bottomRightRadius: Float, bottomLeftRadius: Float): Path {
        var topLeftRadius = topLeftRadius
        var topRightRadius = topRightRadius
        var bottomRightRadius = bottomRightRadius
        var bottomLeftRadius = bottomLeftRadius

        val left = 0f
        val top = 0f

        val minSize = Math.min(width / 2f, height / 2f)

        topLeftRadius = if (topLeftRadius < 0f) 0f else topLeftRadius
        topRightRadius = if (topRightRadius < 0f) 0f else topRightRadius
        bottomLeftRadius = if (bottomLeftRadius < 0f) 0f else bottomLeftRadius
        bottomRightRadius = if (bottomRightRadius < 0f) 0f else bottomRightRadius

        if (topLeftRadius > minSize) {
            topLeftRadius = minSize
        }
        if (topRightRadius > minSize) {
            topRightRadius = minSize
        }
        if (bottomLeftRadius > minSize) {
            bottomLeftRadius = minSize
        }
        if (bottomRightRadius > minSize) {
            bottomRightRadius = minSize
        }

        return Path().apply {
            moveTo(left + topLeftRadius, top)
            lineTo(width - topRightRadius, top)

            //float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo
            arcTo(RectF(width - topRightRadius * 2f, top, width, top + topRightRadius * 2f), -90f, 90f)
            lineTo(width, height - bottomRightRadius)
            arcTo(RectF(width - bottomRightRadius * 2f, height - bottomRightRadius * 2f, width, height), 0f, 90f)
            lineTo(left + bottomLeftRadius, height)
            arcTo(RectF(left, height - bottomLeftRadius * 2f, left + bottomLeftRadius * 2f, height), 90f, 90f)
            lineTo(left, top + topLeftRadius)
            arcTo(RectF(left, top, left + topLeftRadius * 2f, top + topLeftRadius * 2f), 180f, 90f)
            close()
        }
    }

    fun generatePath(rect: RectF, topLeftRadius: Float, topRightRadius: Float, bottomRightRadius: Float, bottomLeftRadius: Float): Path {
        var topLeftRadius = topLeftRadius
        var topRightRadius = topRightRadius
        var bottomRightRadius = bottomRightRadius
        var bottomLeftRadius = bottomLeftRadius

        val minSize = Math.min(rect.width() / 2f, rect.height() / 2f)

        topLeftRadius = if (topLeftRadius < 0f) 0f else topLeftRadius
        topRightRadius = if (topRightRadius < 0f) 0f else topRightRadius
        bottomLeftRadius = if (bottomLeftRadius < 0f) 0f else bottomLeftRadius
        bottomRightRadius = if (bottomRightRadius < 0f) 0f else bottomRightRadius

        if (topLeftRadius > minSize) {
            topLeftRadius = minSize
        }
        if (topRightRadius > minSize) {
            topRightRadius = minSize
        }
        if (bottomLeftRadius > minSize) {
            bottomLeftRadius = minSize
        }
        if (bottomRightRadius > minSize) {
            bottomRightRadius = minSize
        }

        return Path().apply {
            moveTo(rect.left + topLeftRadius, rect.top)
            lineTo(rect.right - topRightRadius, rect.top)
            quadTo(rect.right, rect.top, rect.right, rect.top + topRightRadius)
            lineTo(rect.right, rect.bottom - bottomRightRadius)
            quadTo(rect.right, rect.bottom, rect.right - bottomRightRadius, rect.bottom)
            lineTo(rect.left + bottomLeftRadius, rect.bottom)
            quadTo(rect.left, rect.bottom, rect.left, rect.bottom - bottomLeftRadius)
            lineTo(rect.left, rect.top + topLeftRadius)
            quadTo(rect.left, rect.top, rect.left + topLeftRadius, rect.top)
            close()
        }
    }

}
