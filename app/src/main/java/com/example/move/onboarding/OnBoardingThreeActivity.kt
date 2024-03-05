package com.example.move.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.move.R
import com.example.move.sign.sign_up.SignUpActivity

class OnBoardingThreeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        val btnhome = findViewById<Button>(R.id.btn_home3)
        btnhome.setOnClickListener {
            val intent = Intent(this@OnBoardingThreeActivity, SignUpActivity::class.java)
            startActivity(intent)

        }
    }
}