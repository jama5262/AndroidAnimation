package com.example.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.ramijemli.easings.Easings as ExternalEasing
import com.ramijemli.easings.Interpolators

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()
    private var defaultDuration: Long = 1000L
    private var defaultDelay: Long = 0L
    private var defaultEasing: Easings = Easings.LINEAR

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

    fun x(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("x", dur, delay, easing, *values)
    }

    fun y(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("y", dur, delay, easing, *values)
    }

    fun translateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("translationX", dur, delay, easing, *values)
    }

    fun translateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("translationY", dur, delay, easing, *values)
    }

    fun rotateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("rotationX", dur, delay, easing, *values)
    }

    fun rotateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("rotationY", dur, delay, easing, *values)
    }

    fun rotate(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("rotation", dur, delay, easing, *values)
    }

    fun scaleX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("scaleX ", dur, delay, easing, *values)
    }

    fun scaleY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("scaleY", dur, delay, easing, *values)
    }

    fun alpha(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easings = defaultEasing) {
        createObjectAnimator("alpha", dur, delay, easing, *values)
    }

    private fun createObjectAnimator(propertyName: String, dur: Long, delay: Long, easing: Easings, vararg values: Float) {
        views.forEach { view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                duration = dur
                startDelay = delay
                interpolator = Interpolators(ExternalEasing.valueOf(easing.name))
                setPropertyName(propertyName)
                setFloatValues(*values)
            }
            objectAnimators.add(objectAnimator)
        }
    }

    fun thenPlay(delay: Long) {

    }

    fun duration(dur: Long) {
        defaultDuration = dur
    }

    fun delay(delay: Long) {
        defaultDelay = delay
    }

    fun easing(easing: Easings) {
        defaultEasing = easing
    }

    fun start() {
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            start()
        }
    }

}