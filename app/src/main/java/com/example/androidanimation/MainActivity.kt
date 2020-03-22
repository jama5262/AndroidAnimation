package com.example.androidanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_animation.AndroidAnimation
import com.example.android_animation.enums.Easing
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            AndroidAnimation().apply {
                targetViews(buttonAnimate1, buttonAnimate2, buttonAnimate3, stagger = 1000, reverse = true)
                translateY(0f, 100f)
                targetChildViews(linearLayoutAnimate, stagger = 1000, reverse = true)
                translateY(0f, 100f)
                onAnimationEnd {

                }
                onAnimationStart {

                }
                start()
            }
        }
    }
}
