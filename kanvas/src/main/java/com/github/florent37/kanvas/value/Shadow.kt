package com.github.florent37.kanvas.value

import android.graphics.Color
import android.support.annotation.ColorInt
import com.github.florent37.kanvas.shape.Shape

class Shadow(private val shape: Shape) {
    var shadowRadius = 0f
        set(value) {
            field = value
            update()
        }
    var shadowDx = 0f
        set(value) {
            field = value
            update()
        }
    var shadowDy = 0f
        set(value) {
            field = value
            update()
        }
    @ColorInt
    var shadowColor = Color.BLACK
        set(value) {
            field = value
            update()
        }

    private fun update(){
        if (shadowRadius != 0f) {
            shape.paint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor)
        } else {
            shape.paint.clearShadowLayer()
        }

        shape.update()
    }
}