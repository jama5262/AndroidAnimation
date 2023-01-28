package com.jama.android_animation

import android.animation.AnimatorSet

class Animation(private val animatorSet: AnimatorSet) {

    fun start() {
        animatorSet.start()
    }

    fun pause() {
        animatorSet.pause()
    }

    fun resume() {
        animatorSet.resume()
    }

    fun cancel() {
        animatorSet.cancel()
    }

    fun end() {
        animatorSet.end()
    }
}