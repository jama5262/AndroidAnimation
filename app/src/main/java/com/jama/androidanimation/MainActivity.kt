package com.jama.androidanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jama.android_animation.androidAnimation
import com.jama.android_animation.enums.Easing
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = androidAnimation {
            duration = 5000
            easing = Easing.LINEAR
            targetViews(views = listOf(buttonAnimate1), reverse = true)
            translateX(values = listOf(100f))
            thenPlay()
            easing = Easing.EXP_OUT
            rotate(values = listOf(360f))

            onAnimationStarted { println("Animation is starting") }
            onAnimationPaused { println("Animation is paused") }
            onAnimationResumed { println("Animation is resume") }
            onAnimationCanceled { println("Animation is canceled") }
            onAnimationEnded { println("Animations has ended") }
        }

        button.setOnClickListener {
            animation.start()
        }
    }
}
