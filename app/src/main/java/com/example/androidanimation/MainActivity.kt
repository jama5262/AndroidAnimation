package com.example.androidanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_animation.AndroidAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            AndroidAnimation().apply {
                targetViews(buttonAnimate1, buttonAnimate2)
                translateX(0f, 700f, 0f)
                duration(5000)
                targetChildViews(linearLayoutAnimate)
                translateY(0f, 700f, 0f)
                start()
            }
        }

    }
}
