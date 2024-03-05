package com.example.move.sign.sign_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.move.R
import com.example.move.homescreen.HomeScreenActivity
import com.example.move.sign.sign_up.SignUpActivity
import com.example.move.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SignInActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_sign_in)

            mDatabase = FirebaseDatabase.getInstance().getReference("User")
            preferences = Preferences(this)

            preferences.setValues("onboarding", "1")
          if (preferences.getValues("status").equals("1")) {
              finishAffinity()

              val intent = Intent(this@SignInActivity,
                  HomeScreenActivity::class.java)
              startActivity(intent)
          }

          val btnDaftar = findViewById<Button>(R.id.btn_daftar)
          val btnLogin = findViewById<Button>(R.id.btn_login)
          val etUsername = findViewById<EditText>(R.id.et_username)
          val etPassword = findViewById<EditText>(R.id.et_password)

          btnDaftar.setOnClickListener {
              val intent = Intent(this, SignUpActivity::class.java)
              startActivity(intent)
          }

//          binding.resetpassword.setOnClickListener {
//              val intent = Intent(this, ResetPasswordActivity::class.java)
//              startActivity(intent)
// }
          btnLogin.setOnClickListener {
              val email = etUsername.text.toString()
              val password = etPassword.text.toString()

              if(email.isEmpty()){
                  etUsername.error = "Email tidak boleh kosong"
                  etUsername.requestFocus()
                  return@setOnClickListener
              }
              if(password.isEmpty()){
                  etPassword.error = "Password tidak boleh kosong"
                  etPassword.requestFocus()
                  return@setOnClickListener
              }
              pushLogin(email,password)


          }
      }

    private fun pushLogin(email: String, password: String) {
        mDatabase.child(email).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()

                } else {
                    if (user.Password.equals(password)){
                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG).show()

                        preferences.setValues("nama", user.Name.toString())
                        preferences.setValues("user", user.Username.toString())
                        preferences.setValues("url", user.Url.toString())
                        preferences.setValues("email", user.Email.toString())
                        preferences.setValues("saldo", user.Saldo.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()

                        val intent = Intent(this@SignInActivity,
                            HomeScreenActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}


