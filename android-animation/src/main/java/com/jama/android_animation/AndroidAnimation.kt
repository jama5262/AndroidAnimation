package com.jama.android_animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnStart
import androidx.core.animation.doOnPause
import androidx.core.animation.doOnResume
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnRepeat
import androidx.core.animation.doOnEnd
import androidx.core.view.children
import com.jama.android_animation.enums.Easing
import com.ramijemli.easings.Easings as ExternalEasing
import com.ramijemli.easings.Interpolators

class AndroidAnimation {

    private var objectAnimators = mutableListOf<ObjectAnimator>()
    private var views = mutableListOf<View>()

    private var defaultStagger: Long = 0L
    private var totalObjectAnimatorDuration: Long = 0L

    var duration: Long = 2000L
    var delay: Long = 0L
    var easing: Easing = Easing.ELASTIC_OUT
    var loop: Boolean = false

    private var onAnimationStarted: (() -> Unit)? = null
    private var onAnimationPaused: (() -> Unit)? = null
    private var onAnimationResumed: (() -> Unit)? = null
    private var onAnimationRepeated: (() -> Unit)? = null
    private var onAnimationCanceled: (() -> Unit)? = null
    private var onAnimationEnded: (() -> Unit)? = null

    fun targetViews(
        views: Collection<View>,
        stagger: Long = 0L,
        reverse: Boolean = false
    ) {
        clearViews()
        defaultStagger = stagger
        val targetViews = if (reverse) views.toList().reversed() else views.toList()
        targetViews.forEach { this.views.add(it) }
    }

    fun targetChildViews(
        viewGroups: Collection<ViewGroup>,
        stagger: Long = 0L,
        reverse: Boolean = false
    ) {
        clearViews()
        defaultStagger = stagger
        viewGroups.forEach { viewGroup ->
            val children = viewGroup.children.toList()
            val targetViews = if (reverse) children.reversed() else children
            targetViews.forEach { views.add(it) }
        }
    }

    private fun clearViews() {
        views.clear()
    }

    fun x(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("x", duration, delay, easing, values)
    }

    fun y(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("y", duration, delay, easing, values)
    }

    fun xy(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        x(duration = duration, delay = delay, easing = easing, values = values)
        y(duration = duration, delay = delay, easing = easing, values = values)
    }

    fun translateX(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("translationX", duration, delay, easing, values)
    }

    fun translateY(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("translationY", duration, delay, easing, values)
    }
    
    fun translate(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        translateX(duration = duration, delay =  delay, easing = easing, values = values)
        translateY(duration = duration, delay =  delay, easing = easing, values = values)
    }

    fun rotateX(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("rotationX", duration, delay, easing, values)
    }

    fun rotateY(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("rotationY", duration, delay, easing, values)
    }

    fun rotate(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("rotation", duration, delay, easing, values)
    }

    fun scaleX(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("scaleX", duration, delay, easing, values)
    }

    fun scaleY(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("scaleY", duration, delay, easing, values)
    }

    fun scale(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        scaleX(duration = duration, delay = delay, easing = easing, values = values)
        scaleY(duration = duration, delay = delay, easing = easing, values = values)
    }

    fun alpha(
        values: Collection<Float>,
        duration: Long = this.duration,
        delay: Long = this.delay,
        easing: Easing = this.easing
    ) {
        createObjectAnimator("alpha", duration, delay, easing, values)
    }

    fun thenPlay(delay: Long = 0L) {
        if (this.delay < totalObjectAnimatorDuration) {
            this.delay = totalObjectAnimatorDuration.plus(delay)
        }
    }

    private fun createObjectAnimator(
        propertyName: String,
        dur: Long,
        delay: Long,
        easing: Easing,
        values: Collection<Float>
    ) {
        views.forEachIndexed { index, view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                duration = dur
                startDelay = calculateStaggering(delay, index)
                interpolator = Interpolators(ExternalEasing.valueOf(easing.name))
                setPropertyName(propertyName)
                setFloatValues(*values.toFloatArray())
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

    fun onAnimationStarted(action: () -> Unit) {
        onAnimationStarted = action
    }

    fun onAnimationPaused(action: () -> Unit) {
        onAnimationPaused = action
    }

    fun onAnimationResumed(action: () -> Unit) {
        onAnimationResumed = action
    }

    fun onAnimationRepeated(action: () -> Unit) {
        onAnimationRepeated = action
    }

    fun onAnimationCanceled(action: () -> Unit) {
        onAnimationCanceled = action
    }

    fun onAnimationEnded(action: () -> Unit) {
        onAnimationEnded = action
    }

    private fun loopAnimation(animatorSet: AnimatorSet) {
        if (loop) animatorSet.start()
    }

    internal fun animatorSet(): Animation {
        val animatorSet = AnimatorSet().apply {
            playTogether(objectAnimators.toList())

            doOnStart { onAnimationStarted?.invoke() }
            doOnPause { onAnimationPaused?.invoke() }
            doOnResume { onAnimationResumed?.invoke() }
            doOnRepeat { onAnimationRepeated?.invoke() }
            doOnCancel { onAnimationCanceled?.invoke() }
            doOnEnd {
                loopAnimation(this)
                onAnimationEnded?.invoke()
            }
        }
        return Animation(animatorSet)
    }
}

fun androidAnimation(block: AndroidAnimation.() -> Unit): Animation {
    return AndroidAnimation().apply(block).animatorSet()
}