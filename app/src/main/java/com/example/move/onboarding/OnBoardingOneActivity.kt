package com.example.move.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.move.sign.sign_in.SignInActivity
import com.example.move.R
import com.example.move.utils.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnBoardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        val btndaftar = findViewById<Button>(R.id.btn_daftar)
        btndaftar.setOnClickListener {
            preferences.setValues("onboarding", "1")
            finishAffinity()
            val intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
        val btnhome = findViewById<Button>(R.id.btn_home)
        btnhome.setOnClickListener {
            val intent = Intent(this@OnBoardingOneActivity, OnBoardingTwoActivity::class.java)
            startActivity(intent)
        }
    }
}