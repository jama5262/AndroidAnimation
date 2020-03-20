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
                targetChildViews(linearLayoutAnimate, stagger = 1000)
                delay = 500
                translateX(0f, 700f, 0f)
                thenPlay(2000)
                translateY(0f, 700f, 0f, delay = 400)
                start()
            }
        }
    }
}
