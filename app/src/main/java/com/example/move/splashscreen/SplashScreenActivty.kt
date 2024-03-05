package com.example.move.splashscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.move.R
import com.example.move.onboarding.OnBoardingOneActivity

class SplashScreenActivty : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivty,
                OnBoardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}


