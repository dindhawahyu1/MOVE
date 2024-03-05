package com.example.move.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.move.R
import com.example.move.checkout.adapter.CheckoutAdapter
import com.example.move.homescreen.model.Checkout
import com.example.move.homescreen.model.Film
import com.example.move.utils.Preferences
import java.text.NumberFormat
import java.util.Locale

class CheckOutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total : Int = 0

    private lateinit var preferences : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        preferences = Preferences(this)
        @Suppress("DEPRECATION")
        dataList =intent.getSerializableExtra("data")as ArrayList<Checkout>
        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        val rc_checkout = findViewById<RecyclerView>(R.id.recyclerView)
        val tv_saldo = findViewById<TextView>(R.id.textView42)
        val btn_tiket = findViewById<Button>(R.id.button10)
        val btn_cancel = findViewById<Button>(R.id.button11)
        val tv_total = findViewById<TextView>(R.id.textView34)
        val iv_back = findViewById<ImageView>(R.id.imageView10)

        iv_back.setOnClickListener {
            finish()
        }
        btn_tiket.setOnClickListener {
            val intent = Intent(this@CheckOutActivity, PaymentSuccessActivity::class.java)
            startActivity(intent)
        }
        btn_cancel.setOnClickListener {
            finish()
        }
        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList){
        }

//        if (preferences.getValues("Saldo")!!.isNotEmpty()) {
//            val localeID = Locale("in", "ID")
//            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
//            tv_saldo.setText(formatRupiah.format(preferences.getValues("Saldo")!!.toDouble()))
//            btn_tiket.visibility = View.VISIBLE
//            tv_total.visibility = View.INVISIBLE
//        }else{
//            tv_saldo.text = "Rp.0"
//            btn_tiket.visibility = View.INVISIBLE
//            tv_total.visibility = View.VISIBLE
//                    tv_total.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
//                            "untuk melakukan transaksi"
//        }
    }
}