package com.example.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.ramijemli.easings.Easings
import com.ramijemli.easings.Interpolators

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()
    private var defaultDuration: Long = 1000L
    private var defaultDelay: Long = 0L

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

    fun x(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("x", dur, delay, *values)
    }

    fun y(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("y", dur, delay, *values)
    }

    fun translateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("translationX", dur, delay, *values)
    }

    fun translateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("translationY", dur, delay, *values)
    }

    fun rotateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("rotationX", dur, delay, *values)
    }

    fun rotateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("rotationY", dur, delay, *values)
    }

    fun rotate(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("rotation", dur, delay, *values)
    }

    fun scaleX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("scaleX ", dur, delay, *values)
    }

    fun scaleY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("scaleY", dur, delay, *values)
    }

    fun alpha(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay) {
        createObjectAnimator("alpha", dur, delay, *values)
    }

    private fun createObjectAnimator(propertyName: String, dur: Long, delay: Long, vararg values: Float) {
        views.forEach { view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                duration = dur
                startDelay = delay
                interpolator = Interpolators(Easings.BACK_OUT)
                setPropertyName(propertyName)
                setFloatValues(*values)
            }
            objectAnimators.add(objectAnimator)
        }
    }

    fun duration(dur: Long) {
        defaultDuration = dur
    }

    fun delay(delay: Long) {
        defaultDelay = delay
    }

//    private fun

    fun start() {
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            start()
        }
    }

}