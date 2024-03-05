package com.example.move.homescreen.dashboard

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.move.checkout.PilihTempatDudukActivity
import com.example.move.databinding.ActivityDetailBinding
import com.example.move.homescreen.model.Film
import com.example.move.homescreen.model.Plays
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var mDatabase :DatabaseReference
    private var dataList= ArrayList<Plays>()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Film>("data")

        mDatabase =FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.judul.toString())
            .child("play")

        binding.textView16.text =data.judul
        binding.textView17.text =data.genre
        binding.textView19.text =data.desc
        binding.textView20.text =data.rating

        Glide.with(this)
            .load(data.poster)
            .into(binding.videoView)

        binding.button7.setOnClickListener{
        val intent = Intent(this,
            PilihTempatDudukActivity::class.java).putExtra("data", data)
        startActivity(intent)
        }

        binding.imageView5.setOnClickListener{
            finish()
        }

        binding.rvPlayer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()


    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getdatasnapshot in dataSnapShot.children ) {
                    val film = getdatasnapshot.getValue(Plays::class.java)
                    dataList.add(film!!)
                }
                binding.rvPlayer.adapter = PlaysAdapter(dataList){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}