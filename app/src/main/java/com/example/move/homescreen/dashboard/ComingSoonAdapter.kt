package com.example.move.homescreen.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.move.R
import com.example.move.homescreen.model.Film


class ComingSoonAdapter(private var data: List<Film>,
                        private val listener: (Film) -> Unit) :
    RecyclerView.Adapter<ComingSoonAdapter.LeagueViewHolder>() {

        lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_coming_soon, parent, false)
        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size


    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<TextView>(R.id.tv_kursi)
        private val tvGenre = view.findViewById<TextView>(R.id.tv_genre)
        private val tvRating = view.findViewById<TextView>(R.id.tv_rating)

        private val tvImage = view.findViewById<ImageView>(R.id.iv_poster_image)

        fun bindItem(data: Film, listener:(Film) -> Unit, context: Context) {
            tvTitle.text = data.judul
            tvGenre.text = data.genre
            tvRating.text = data.rating

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }


}