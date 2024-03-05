package com.example.move.checkout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.move.R
import com.example.move.homescreen.model.Checkout
import com.example.move.homescreen.model.Film
import java.text.NumberFormat
import java.util.Locale


class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener: (Checkout) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.LeagueViewHolder>() {

        lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return LeagueViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter, position)
    }

    override fun getItemCount(): Int = data.size


    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<TextView>(R.id.tv_kursiq)
        private val tvHarga = view.findViewById<TextView>(R.id.tv_harga)

        fun bindItem(data: Checkout, listener:(Checkout) -> Unit, context: Context, position: Int) {
            val localeID = Locale("in","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")) {
                tvTitle.text = data.kursi
                tvTitle.setCompoundDrawables(null, null, null, null)
            }else{
                tvTitle.text = "Seat No.  "+data.kursi
            }
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }


}