package com.example.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.example.android_animation.enums.Easing
import com.ramijemli.easings.Easings as ExternalEasing
import com.ramijemli.easings.Interpolators

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()
    private var defaultDuration: Long = 1000L
    private var defaultDelay: Long = 0L
    private var defaultEasing: Easing = Easing.LINEAR
    private var defaultStagger: Long = 0L
    private var totalObjectAnimatorDuration: Long = 0L

    fun targetViews(vararg v: View, stagger: Long = 0L) {
        defaultStagger = stagger
        clearViews()
        v.forEach { view ->
            views.add(view)
        }
    }

    fun targetChildViews(vararg vg: ViewGroup, stagger: Long = 0L) {
        defaultStagger = stagger
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

    fun x(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("x", dur, delay, easing, *values)
    }

    fun y(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("y", dur, delay, easing, *values)
    }

    fun translateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("translationX", dur, delay, easing, *values)
    }

    fun translateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("translationY", dur, delay, easing, *values)
    }

    fun rotateX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("rotationX", dur, delay, easing, *values)
    }

    fun rotateY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("rotationY", dur, delay, easing, *values)
    }

    fun rotate(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("rotation", dur, delay, easing, *values)
    }

    fun scaleX(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("scaleX ", dur, delay, easing, *values)
    }

    fun scaleY(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("scaleY", dur, delay, easing, *values)
    }

    fun alpha(vararg values: Float, dur: Long = defaultDuration, delay: Long = defaultDelay, easing: Easing = defaultEasing) {
        createObjectAnimator("alpha", dur, delay, easing, *values)
    }

    private fun createObjectAnimator(propertyName: String, dur: Long, delay: Long, easing: Easing, vararg values: Float) {
        views.forEachIndexed { index, view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                duration = dur
                startDelay = calculateStaggering(delay, index)
                interpolator = Interpolators(ExternalEasing.valueOf(easing.name))
                setPropertyName(propertyName)
                setFloatValues(*values)
            }
            getTotalDuration(objectAnimator)
            objectAnimators.add(objectAnimator)
        }
    }

    private fun calculateStaggering(delay: Long, index: Int): Long {
        if (defaultStagger == 0L) {
            return delay
        }
        return (defaultStagger * (index)) + delay
    }

    private fun getTotalDuration(objectAnimator: ObjectAnimator) {
        val totalDuration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            objectAnimator.totalDuration
        } else {
            objectAnimator.duration + objectAnimator.startDelay
        }

        if (totalObjectAnimatorDuration < totalDuration) {
            totalObjectAnimatorDuration = totalDuration
        }
    }

    fun thenPlay(delay: Long = 0L) {
        if (defaultDelay < totalObjectAnimatorDuration) {
            defaultDelay = totalObjectAnimatorDuration.plus(delay)
        }
    }

    fun duration(dur: Long) {
        defaultDuration = dur
    }

    fun delay(delay: Long) {
        defaultDelay = delay
    }

    fun easing(easing: Easing) {
        defaultEasing = easing
    }

    fun start() {
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            start()
        }
    }

}