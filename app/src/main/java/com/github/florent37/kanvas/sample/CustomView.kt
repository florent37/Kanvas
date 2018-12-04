package com.github.florent37.kanvas.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.florent37.kanvas.anim.obtainCanvasAnimator
import com.github.florent37.kanvas.dpToPx
import com.github.florent37.kanvas.draw
import com.github.florent37.kanvas.shape.rectShape

class CustomView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    val canvasAnimator = obtainCanvasAnimator()

    val background = rectShape { view ->
        this.color = Color.parseColor("#6fbf73")
        this.cornerRadius = 16.dpToPx(view)

        left = 100f
        width = view.width / 2f
        top = 100f
        height = view.height / 3f
    }

    init {
        setWillNotDraw(false)
        setOnClickListener {
            canvasAnimator
                    .play(background.animate().right.to(this.width.toFloat()))
                    .start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.draw(background)
    }

}
