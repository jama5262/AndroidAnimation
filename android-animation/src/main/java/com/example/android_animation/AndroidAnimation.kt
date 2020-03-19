package com.example.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()
    private var defaultDuration: Long = 1000L

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

    fun x(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("x", dur, *values)
    }

    fun y(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("y", dur, *values)
    }

    fun translateX(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("translationX", dur, *values)
    }

    fun translateY(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("translationY", dur, *values)
    }

    fun rotateX(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("rotationX", dur, *values)
    }

    fun rotateY(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("rotationY", dur, *values)
    }

    fun rotate(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("rotation", dur, *values)
    }

    fun scaleX(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("scaleX ", dur, *values)
    }

    fun scaleY(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("scaleY", dur, *values)
    }

    fun alpha(vararg values: Float, dur: Long = defaultDuration) {
        createObjectAnimator("alpha", dur, *values)
    }

    private fun createObjectAnimator(propertyName: String, dur: Long, vararg values: Float) {
        views.forEach { view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                duration = dur
                setPropertyName(propertyName)
                setFloatValues(*values)
            }
            objectAnimators.add(objectAnimator)
        }
    }

    fun duration(dur: Long) {
        defaultDuration = dur
    }

    fun start() {
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            start()
        }
    }

}