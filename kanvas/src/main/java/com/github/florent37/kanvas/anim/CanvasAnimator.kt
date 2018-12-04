package com.github.florent37.kanvas.anim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.github.florent37.kanvas.anim.core.InvalidateListener
import com.github.florent37.kanvas.anim.core.ViewInvalidateListener
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

fun View.obtainCanvasAnimator() = CanvasAnimator(this)

class CanvasAnimator(var invalidateListener: InvalidateListener?) {

    private val animators = mutableListOf<ValueAnimator>()

    var repeatCount = 0
    var duration = 300L
    var startDelay = 0L

    var interpolator: Interpolator = LinearInterpolator()

    private val initActions = mutableListOf<(() -> Unit)>()
    private val onAnimationStarts = mutableListOf<(() -> Unit)>()
    private val onAnimationEnds = mutableListOf<(() -> Unit)>()
    private val endedAnimationsCount = AtomicInteger(0)

    private var started = false

    constructor(invalidateListener: InvalidateListener, animators: Collection<ValueAnimator>) : this(invalidateListener) {
        play(animators)
    }

    constructor(view: View) : this(ViewInvalidateListener(view))

    constructor(view: View, animators: Collection<ValueAnimator>) : this(ViewInvalidateListener(view), animators)

    fun clear(): CanvasAnimator {
        animators.forEach { it.cancel() }
        initActions.clear()
        onAnimationStarts.clear()
        onAnimationEnds.clear()
        animators.clear()
        started = false
        return this
    }

    fun play(animators: Collection<ValueAnimator>): CanvasAnimator {
        this.animators.addAll(animators)
        return this
    }

    fun play(vararg animators: ValueAnimator): CanvasAnimator {
        if (animators != null) {
            this.animators.addAll(Arrays.asList(*animators))
        }
        return this
    }

    fun start(onAnimationEnd: (() -> Unit)? = null): CanvasAnimator {
        onAnimationEnd?.let {
            this.onAnimationEnd(it)
        }
        if (!started) {
            started = true
            endedAnimationsCount.set(0)

            onAnimationStarts.forEach {
                it.invoke()
            }
            //do not use AnimatorSet because you cannot use setRepeatCount

            val animationCount = animators.size

            animators.forEach { animator ->
                animator.repeatCount = repeatCount
                animator.duration = duration
                animator.interpolator = interpolator
                animator.startDelay = startDelay
                animator.addUpdateListener {
                    invalidateListener?.invalidate()
                }
                animator.addListener(
                        object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                val finishedAnims = endedAnimationsCount.incrementAndGet()
                                if (animationCount == finishedAnims) {
                                    onAnimationEnds.forEach { it.invoke() }
                                }
                            }
                        }
                )

                initActions.forEach { it.invoke() }

                animator.start()
            }
        }
        return this
    }

    fun onAnimationEnd(onAnimationEnd: (() -> Unit)?): CanvasAnimator {
        onAnimationEnd?.let {
            this.onAnimationEnds.add(it)
        }
        return this
    }

    fun onAnimationStart(onAnimationStart: (() -> Unit)?): CanvasAnimator {
        onAnimationStart?.let {
            this.onAnimationStarts.add(it)
        }
        return this
    }

    fun withInitAction(initAction: (() -> Unit)?): CanvasAnimator {
        initAction?.let {
            this.initActions.add(it)
        }
        return this
    }

}
