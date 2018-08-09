package com.github.florent37.kanvas.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.florent37.kanvas.anim.CanvasAnimator
import com.github.florent37.kanvas.draw
import com.github.florent37.kanvas.shape.RectShape

class CustomView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    val canvasAnimator = CanvasAnimator(this)

    val background = RectShape().apply {
        this.color = Color.BLACK
    }


    init {
        setWillNotDraw(false)
        setOnClickListener{
            canvasAnimator
                    .play(background.animate().right.equal(600f))
                    .start()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        background.apply {
            left = 100f
            width = 100f

            top = 100f
            height = 50f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.draw(background)
    }

}
