package com.example.android_animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()

    fun targetViews(vararg v: View) {
        clearViews()
        v.forEach { view ->
            views.add(view)
        }
    }

    fun targetChildViews(vararg vg: ViewGroup) {
        clearViews()
        vg.forEach { viewGroup ->
            viewGroup.children.forEach { view ->
                views.add(view)
            }
        }
    }

    private fun clearViews() {
        views = mutableListOf()
    }

    fun x(vararg values: Float) {

    }

    fun y(vararg values: Float) {

    }

    fun translateX(vararg values: Float) {

    }

    fun translateY(vararg values: Float) {

    }

    fun rotateX(vararg values: Float) {

    }

    fun rotateY(vararg values: Float) {

    }

    fun rotate(vararg values: Float) {

    }

    fun scaleX(vararg values: Float) {

    }

    fun scaleY(vararg values: Float) {

    }

    fun alpha(vararg values: Float) {

    }

}