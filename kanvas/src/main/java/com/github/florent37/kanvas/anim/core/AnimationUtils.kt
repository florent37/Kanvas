package com.github.florent37.kanvas.anim.core

internal fun insertAtFirst(value: Float, values: FloatArray): FloatArray {
    val newValues = FloatArray(values.size + 1)
    newValues[0] = value
    for (i in values.indices) {
        newValues[i + 1] = values[i]
    }
    return newValues
}

internal fun insertAtFirst(value: Int, values: IntArray): IntArray {
    val newValues = IntArray(values.size + 1)
    newValues[0] = value
    for (i in values.indices) {
        newValues[i + 1] = values[i]
    }
    return newValues
}