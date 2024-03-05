package com.example.move.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.move.R
import com.example.move.homescreen.HomeScreenActivity

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val btn_home =findViewById<Button>(R.id.button12)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }
    }
}