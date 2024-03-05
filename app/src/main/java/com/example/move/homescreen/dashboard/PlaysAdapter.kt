package com.example.move.homescreen.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.move.R
import com.example.move.homescreen.model.Film
import com.example.move.homescreen.model.Plays


class PlaysAdapter(private var data: List<Plays>,
                   private val listener: (Plays) -> Unit) :
    RecyclerView.Adapter<PlaysAdapter.LeagueViewHolder>() {

        lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_play, parent, false)

        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.findViewById<TextView>(R.id.tv_kursi)
        private val tvimage = view.findViewById<ImageView>(R.id.iv_poster_image)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context : Context, position : Int){

            tvTitle.text = data.nama

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvimage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }


}