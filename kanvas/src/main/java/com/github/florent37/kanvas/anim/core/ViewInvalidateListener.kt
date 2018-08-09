package com.github.florent37.kanvas.anim.core

import android.view.View

import java.lang.ref.Reference
import java.lang.ref.WeakReference

class ViewInvalidateListener(view: View) : InvalidateListener {
    private val viewReference: Reference<View> = WeakReference(view)

    override fun invalidate() {
        viewReference.get()?.postInvalidate()
    }
}