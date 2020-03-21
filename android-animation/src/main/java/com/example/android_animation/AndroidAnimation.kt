package com.example.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.view.children
import com.example.android_animation.enums.Direction
import com.example.android_animation.enums.Easing
import com.ramijemli.easings.Easings as ExternalEasing
import com.ramijemli.easings.Interpolators

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()
    var duration: Long = 2000L
    var delay: Long = 0L
    var easing: Easing = Easing.ELASTIC_OUT
    var direction: Direction = Direction.NORMAL
    var loop: Boolean = false
    private var defaultStagger: Long = 0L
    private var totalObjectAnimatorDuration: Long = 0L
    private var onAnimationEnd: (() -> Unit)? = null
    private var onAnimationStart: (() -> Unit)? = null

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

    fun x(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("x", duration, delay, easing, *values)
    }

    fun y(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("y", duration, delay, easing, *values)
    }

    fun translateX(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("translationX", duration, delay, easing, *values)
    }

    fun translateY(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("translationY", duration, delay, easing, *values)
    }

    fun rotateX(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("rotationX", duration, delay, easing, *values)
    }

    fun rotateY(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("rotationY", duration, delay, easing, *values)
    }

    fun rotate(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("rotation", duration, delay, easing, *values)
    }

    fun scaleX(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("scaleX", duration, delay, easing, *values)
    }

    fun scaleY(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("scaleY", duration, delay, easing, *values)
    }

    fun alpha(vararg values: Float, duration: Long = this.duration, delay: Long = this.delay, easing: Easing = this.easing) {
        createObjectAnimator("alpha", duration, delay, easing, *values)
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

    fun thenPlay(d: Long = 0L) {
        if (delay < totalObjectAnimatorDuration) {
            delay = totalObjectAnimatorDuration.plus(d)
        }
    }

    private fun animationDirection(animatorSet: AnimatorSet) {
        when(direction) {
            Direction.NORMAL -> animatorSet.start()
            Direction.REVERSE -> animatorSet.reverse()
        }
    }

    fun onAnimationStart(action: () -> Unit) {
        onAnimationStart = action
    }

    fun onAnimationEnd(action: () -> Unit) {
        onAnimationEnd = action
    }

    private fun loopAnimation(animatorSet: AnimatorSet) {
        if (loop) animatorSet.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun start() {
        onAnimationStart?.invoke()
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            animationDirection(this)
            doOnEnd {
                loopAnimation(this)
                onAnimationEnd?.invoke()
            }
        }
    }

}