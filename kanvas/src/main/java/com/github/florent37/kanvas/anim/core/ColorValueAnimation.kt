package com.github.florent37.kanvas.anim.core

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator

open class ColorValueAnimation() {

    var getValue: (() -> Int)? = null
    var setValue: ((Int) -> Unit)? = null

    fun to(vararg values: Int) =
            ValueAnimator.ofInt(*insertAtFirst(getValue?.invoke() ?: 0, values)).apply {
                setEvaluator(ArgbEvaluator())
                addUpdateListener {
                    setValue?.invoke(it.animatedValue as Int)
                }
            }

    fun equal(vararg values: Int) =
            ValueAnimator.ofInt(*values).apply {
                setEvaluator(ArgbEvaluator())
                addUpdateListener {
                    setValue?.invoke(it.animatedValue as Int)
                }
            }

    /*
    fun plus(vararg values: Int): ValueAnimator {
        val newValues = IntArray(values.size)
        val initialValue = getValue?.invoke() ?: 0
        values.indices.forEach { i -> newValues[i] = values[i] + initialValue }
        return to(*newValues)
    }
    */
}