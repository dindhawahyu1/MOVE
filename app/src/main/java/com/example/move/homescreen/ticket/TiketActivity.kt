package com.example.move.homescreen.ticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.move.R
import com.example.move.databinding.ActivityTiketBinding
import com.example.move.homescreen.model.Checkout
import com.example.move.homescreen.model.Film

class TiketActivity : AppCompatActivity() {

    lateinit var binding : ActivityTiketBinding
    private var dataList = ArrayList<Checkout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Film>("data")

        binding.textView33.text = data!!.judul
        binding.textView35.text = data.director
        binding.textView36.text = data.rating

        Glide.with(this)
            .load(data.minipic)
            .into(binding.ivPosterImage)

        binding.rcItems.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        binding.rcItems.adapter = TiketAdapter(dataList){
        }
        binding.imageView6.setOnClickListener{
            finish()
        }
    }
}