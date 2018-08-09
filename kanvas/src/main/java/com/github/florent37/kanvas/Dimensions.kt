package com.github.florent37.kanvas

import android.content.Context
import android.view.View

inline fun dpToPx(context: Context, dp: Int): Float {
    return dp * context.resources.displayMetrics.density
}

inline fun dpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}

inline fun dpToPx(view: View, dp: Int): Float {
    return dpToPx(view.context, dp)
}

inline fun dpToPx(view: View, dp: Float): Float {
    return dpToPx(view.context, dp)
}

inline fun Int.dpToPx(context: Context) = dpToPx(context, this)
inline fun Int.dpToPx(view: View) = dpToPx(view.context, this)
inline fun Float.dpToPx(context: Context) = dpToPx(context, this)
inline fun Float.dpToPx(view: View) = dpToPx(view.context, this)
