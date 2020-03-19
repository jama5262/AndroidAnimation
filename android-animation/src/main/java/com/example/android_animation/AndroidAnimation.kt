package com.example.android_animation

import android.animation.AnimatorSet
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
        createObjectAnimator("x", *values)
    }

    fun y(vararg values: Float) {
        createObjectAnimator("y", *values)
    }

    fun translateX(vararg values: Float) {
        createObjectAnimator("translationX", *values)
    }

    fun translateY(vararg values: Float) {
        createObjectAnimator("translationY", *values)
    }

    fun rotateX(vararg values: Float) {
        createObjectAnimator("rotationX", *values)
    }

    fun rotateY(vararg values: Float) {
        createObjectAnimator("rotationY", *values)
    }

    fun rotate(vararg values: Float) {
        createObjectAnimator("rotation", *values)
    }

    fun scaleX(vararg values: Float) {
        createObjectAnimator("scaleX ", *values)
    }

    fun scaleY(vararg values: Float) {
        createObjectAnimator("scaleY", *values)
    }

    fun alpha(vararg values: Float) {
        createObjectAnimator("alpha", *values)
    }

    private fun createObjectAnimator(propertyName: String, vararg values: Float) {
        views.forEach { view ->
            val objectAnimator = ObjectAnimator()
            objectAnimator.apply {
                target = view
                setPropertyName(propertyName)
                setFloatValues(*values)
            }
            objectAnimators.add(objectAnimator)
        }
    }

    fun start() {
        AnimatorSet().apply {
            playTogether(objectAnimators.toList())
            start()
        }
    }

}