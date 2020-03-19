package com.example.androidanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_animation.AndroidAnimation
import com.example.android_animation.Easings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            AndroidAnimation().apply {
                targetViews(buttonAnimate1, buttonAnimate2)
                translateX(0f, 700f, 0f)
                thenPlay()
                duration(5000)
                easing(Easings.QUINT_IN_OUT)
                targetChildViews(linearLayoutAnimate)
                rotate(0f, 700f, 0f)
                start()
            }
        }

    }
}
