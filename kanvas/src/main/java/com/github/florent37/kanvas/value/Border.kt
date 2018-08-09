package com.github.florent37.kanvas.value

import android.graphics.Color
import android.support.annotation.ColorInt
import com.github.florent37.kanvas.shape.Shape

class Border(val rectShape: Shape){
    @ColorInt
    var color: Int = Color.WHITE
        set(value) {
            field = value
            rectShape.update()
        }

    var width = 0f
        set(value) {
            field = value
            rectShape.update()
        }
}