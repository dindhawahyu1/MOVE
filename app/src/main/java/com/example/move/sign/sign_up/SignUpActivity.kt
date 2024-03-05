package com.example.move.sign.sign_up

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.move.databinding.ActivitySignUpBinding
import com.example.move.sign.sign_in.SignInActivity
import com.example.move.sign.sign_in.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SignUpActivity : AppCompatActivity(){

    lateinit var binding : ActivitySignUpBinding
    lateinit var auth : FirebaseAuth
    lateinit var mFirebaseDatabase : DatabaseReference
    lateinit var mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase :DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://movielast1-default-rtdb.firebaseio.com/")

        binding.imageView5.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)}
        binding.btnHome4.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val username = binding.etUsername.text.toString()
            val nama = binding.etName.text.toString()
            if(email.isEmpty() || password.isEmpty() || username.isEmpty() || nama.isEmpty()){
                binding.etEmail.error = "Email tidak boleh kosong"
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etUsername.error = "Username tidak boleh kosong"
                binding.etName.error = "Nama tidak boleh kosong"}
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Email tidak valid"
                binding.etEmail.requestFocus()
                return@setOnClickListener}
            if(password.length < 6){
                binding.etPassword.error = "Password minimal 6 karakter"
                binding.etPassword.requestFocus()
                return@setOnClickListener}
            if(username.length < 6){
                binding.etUsername.error = "Username minimal 6 karakter"
                binding.etUsername.requestFocus()
                return@setOnClickListener }
            else{
                mDatabase = FirebaseDatabase.getInstance().getReference("User")
                mDatabase.child(username).child("Username").setValue(username)
                mDatabase.child(username).child("Email").setValue(email)
                mDatabase.child(username).child("Password").setValue(password)
                mDatabase.child(username).child("Nama").setValue(nama)
                mDatabase.child(username).child("Url").setValue(" ")
                mDatabase.child(username).child("Saldo").setValue(" ")
            }
            RegisterFirebase(email, password)
        }
    }
    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
