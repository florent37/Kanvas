package com.github.florent37.kanvas

class Variables {

    private val variables = mutableMapOf<String, Any>()

    fun set(key: String, value: Any): Variables {
        variables[key] = value
        return this
    }

    fun <T> get(key: String): T {
        return variables[key] as T
    }
}