package com.github.florent37.kanvas.shape

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.view.View
import com.github.florent37.kanvas.Alignment

open class TextShape : RectShape {

    constructor() : super()

    constructor(block: (TextShape.() -> Unit)) : super() {
        block.invoke(this)
    }

    constructor(v: View, block: (TextShape.(view: View) -> Unit)) : super() {
        this.initWhenViewHasSize(v, block = block)
    }

    var text: CharSequence = ""
        set(value) {
            field = value
            update()
        }

    private var staticLayout: StaticLayout? = null

    var verticalAlignment = Alignment.VERTICAL.CENTER
        set(value) {
            field = value
            update()
        }
    var horizontalAlignment = Alignment.HORIZONTAL.CENTER
        set(value) {
            field = value
            update()
        }

    var textSize: Float
        get() = paint.textSize
        set(value) {
            paint.textSize = value
            update()
        }

    var typeface: Typeface
        get() = paint.typeface
        set(value) {
            paint.typeface = value
            update()
        }

    private fun toAlignment(align: Alignment.HORIZONTAL): Layout.Alignment {
        when (align) {
            Alignment.HORIZONTAL.LEFT -> return Layout.Alignment.ALIGN_NORMAL
            Alignment.HORIZONTAL.RIGHT -> return Layout.Alignment.ALIGN_OPPOSITE
            Alignment.HORIZONTAL.CENTER -> return Layout.Alignment.ALIGN_CENTER
            else -> return Layout.Alignment.ALIGN_CENTER
        }
    }

    override fun update() {
        super.update()
        staticLayout = StaticLayout(text, paint, width.toInt(), toAlignment(horizontalAlignment), 1.0f, 0f, false)
    }

    fun computeTextHeight(): Float {
        val rect = Rect()
        paint.getTextBounds(text.toString(), 0, text.length, rect)
        return rect.height().toFloat()
    }

    override fun draw(canvas: Canvas) {
        staticLayout?.let { staticLayout ->
            val saveState = canvas.save()

            val textHeight = calculateHeight()
            if (verticalAlignment === Alignment.VERTICAL.CENTER) {
                canvas.translate(left, centerY - textHeight / 2f)
            } else if (verticalAlignment === Alignment.VERTICAL.TOP) {
                canvas.translate(left, top)
            } else if (verticalAlignment === Alignment.VERTICAL.BOTTOM) {
                canvas.translate(left, bottom - textHeight)
            }

            staticLayout.draw(canvas)

            canvas.restoreToCount(saveState)
        }
    }

    fun calculateHeight(): Float {
        return if (staticLayout == null || text.isBlank()) {
            0f
        } else {
            staticLayout?.height?.toFloat() ?: 0f
        }
    }
}
