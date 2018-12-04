package com.github.florent37.kanvas.shape

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View

open class DrawableShape : RectShape() {

    var drawable: Drawable? = null
        set(value) {
            field = value?.mutate()
        }

    fun setBitmap(bitmap: Bitmap): DrawableShape {
        this.drawable = BitmapDrawable(bitmap)
        return this
    }

    override fun draw(canvas: Canvas) {
        drawable?.setBounds(
                rectF.left.toInt(),
                rectF.top.toInt(),
                rectF.right.toInt(),
                rectF.bottom.toInt()
        )
        drawable?.draw(canvas)
    }
}
