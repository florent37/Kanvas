package com.github.florent37.kanvas.anim.core

import android.animation.ValueAnimator

open class FloatValueAnimation {

    var getValue: (() -> Float)? = null
    var setValue: ((Float) -> Unit)? = null

    fun to(vararg values: Float) =
            ValueAnimator.ofFloat(*insertAtFirst(getValue?.invoke() ?: 0f, values)).apply {
                addUpdateListener {
                    setValue?.invoke(it.animatedValue as Float)
                }
            }

    fun equal(vararg values: Float) =
            ValueAnimator.ofFloat(*values).apply {
                addUpdateListener {
                    setValue?.invoke(it.animatedValue as Float)
                }
            }


    fun by(vararg values: Float): ValueAnimator {
        val newValues = FloatArray(values.size)
        val initialValue = getValue?.invoke() ?: 0f
        values.indices.forEach { i -> newValues[i] = values[i] * initialValue }
        return equal(*newValues)
    }

    fun plus(vararg values: Float): ValueAnimator {
        val newValues = FloatArray(values.size)
        val initialValue = getValue?.invoke() ?: 0f
        values.indices.forEach { i -> newValues[i] = values[i] + initialValue }
        return to(*newValues)
    }
}