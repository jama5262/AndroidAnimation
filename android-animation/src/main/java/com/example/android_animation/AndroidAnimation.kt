package com.example.android_animation

import android.animation.ObjectAnimator
import android.view.View

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()

    fun targetView(vararg v: View) {
        views = mutableListOf()
        v.forEach { view ->
            views.add(view)
        }
    }

}