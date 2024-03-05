package com.example.move.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.move.R
import com.example.move.homescreen.model.Checkout
import com.example.move.homescreen.model.Film

class PilihTempatDudukActivity : AppCompatActivity() {

    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false
    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false
    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false
    var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_tempat_duduk)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Film>("data")

        val tv_kursi = findViewById<TextView>(R.id.textView22)

        tv_kursi.text = data!!.judul

        val btn_buy = findViewById<Button>(R.id.button8)
        val iv_back = findViewById<ImageView>(R.id.imageView5)
        val a1 = findViewById<ImageView>(R.id.a1); val a2 = findViewById<ImageView>(R.id.a2); val a3 = findViewById<ImageView>(R.id.a3); val a4 = findViewById<ImageView>(R.id.a4);
        val b1 = findViewById<ImageView>(R.id.b1); val b2 = findViewById<ImageView>(R.id.b2); val b3 = findViewById<ImageView>(R.id.b3); val b4 = findViewById<ImageView>(R.id.b4);
        val c1 = findViewById<ImageView>(R.id.c1); val c2 = findViewById<ImageView>(R.id.c2); val c3 = findViewById<ImageView>(R.id.c3); val c4 = findViewById<ImageView>(R.id.c4);
        val d1 = findViewById<ImageView>(R.id.d1); val d2 = findViewById<ImageView>(R.id.d2); val d3 = findViewById<ImageView>(R.id.d3); val d4 = findViewById<ImageView>(R.id.d4);

        iv_back.setOnClickListener {
            finish()
        }

        a1.setOnClickListener {
            if (statusA1){
                a1.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusA1 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("A1","25000"))
            }else{
                a1.setImageResource(R.drawable.shape_rectangle_pink)
                statusA1 = true
                total += 1
                belitiket(total)

                val data = Checkout("A1","25000")
                dataList.add(data)
            }
        }
        a2.setOnClickListener {
            if (statusA2){
                a2.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusA2 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("A2","25000"))
            }else{
                a2.setImageResource(R.drawable.shape_rectangle_pink)
                statusA2 = true
                total += 1
                belitiket(total)

                val data = Checkout("A2","25000")
                dataList.add(data)
            }
        }
        a3.setOnClickListener {
            if (statusA3){
                a3.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusA3 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("A3","25000"))
            }else{
                a3.setImageResource(R.drawable.shape_rectangle_pink)
                statusA3 = true
                total += 1
                belitiket(total)

                val data = Checkout("A3","25000")
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            if (statusA4){
                a4.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusA4 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("A4","25000"))
            }else{
                a4.setImageResource(R.drawable.shape_rectangle_pink)
                statusA4 = true
                total += 1
                belitiket(total)

                val data = Checkout("A4","25000")
                dataList.add(data)
            }
        }
        b1.setOnClickListener {
            if (statusB1){
                b1.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusB1 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("B1","25000"))
            }else{
                b1.setImageResource(R.drawable.shape_rectangle_pink)
                statusB1 = true
                total += 1
                belitiket(total)

                val data = Checkout("B1","25000")
                dataList.add(data)
            }
        }
        b2.setOnClickListener {
            if (statusB2){
                b2.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusB2 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("B2","25000"))
            }else{
                b2.setImageResource(R.drawable.shape_rectangle_pink)
                statusB2 = true
                total += 1
                belitiket(total)

                val data = Checkout("B2","25000")
                dataList.add(data)
            }
        }
        b3.setOnClickListener {
            if (statusB3){
                b3.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusB3 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("B3","25000"))
            }else{
                b3.setImageResource(R.drawable.shape_rectangle_pink)
                statusB3 = true
                total += 1
                belitiket(total)

                val data = Checkout("B3","25000")
                dataList.add(data)
            }
        }
        b4.setOnClickListener {
            if (statusB4){
                b4.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusB4 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("B4","25000"))
            }else{
                b4.setImageResource(R.drawable.shape_rectangle_pink)
                statusB4 = true
                total += 1
                belitiket(total)

                val data = Checkout("B4","25000")
                dataList.add(data)
            }
        }
         c1.setOnClickListener {
            if (statusC1){
                c1.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusC1 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("C1","25000"))
            }else{
                c1.setImageResource(R.drawable.shape_rectangle_pink)
                statusC1 = true
                total += 1
                belitiket(total)

                val data = Checkout("C1","25000")
                dataList.add(data)
            }
        }
        c2.setOnClickListener {
            if (statusC2){
                c2.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusC2 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("C2","25000"))
            }else{
                c2.setImageResource(R.drawable.shape_rectangle_pink)
                statusC2 = true
                total += 1
                belitiket(total)

                val data = Checkout("C2","25000")
                dataList.add(data)
            }
        }
        c3.setOnClickListener {
            if (statusC3){
                c3.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusC3 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("C3","25000"))
            }else{
                c3.setImageResource(R.drawable.shape_rectangle_pink)
                statusC3 = true
                total += 1
                belitiket(total)

                val data = Checkout("C3","25000")
                dataList.add(data)
            }
        }
        c4.setOnClickListener {
            if (statusC4){
                c4.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusC4 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("C4","25000"))
            }else{
                c4.setImageResource(R.drawable.shape_rectangle_pink)
                statusC4 = true
                total += 1
                belitiket(total)

                val data = Checkout("D4","25000")
                dataList.add(data)
            }
        }
        d1.setOnClickListener {
            if (statusD1){
                d1.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusD1 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("D1","25000"))
            }else{
                d1.setImageResource(R.drawable.shape_rectangle_pink)
                statusD1 = true
                total += 1
                belitiket(total)

                val data = Checkout("D1","25000")
                dataList.add(data)
            }
        }
        d2.setOnClickListener {
            if (statusD2){
                d2.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusD2 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("D2","25000"))
            }else{
                d2.setImageResource(R.drawable.shape_rectangle_pink)
                statusD2 = true
                total += 1
                belitiket(total)

                val data = Checkout("D2","25000")
                dataList.add(data)
            }
        }
        d3.setOnClickListener {
            if (statusD3){
                d3.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusD3 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("D3","25000"))
            }else{
                d3.setImageResource(R.drawable.shape_rectangle_pink)
                statusD3 = true
                total += 1
                belitiket(total)

                val data = Checkout("D3","25000")
                dataList.add(data)
            }
        }
        d4.setOnClickListener {
            if (statusD4){
                d4.setImageResource(R.drawable.shape_rectangle_emptyseat)
                statusD4 = false
                total -= 1
                belitiket(total)

                dataList.remove(Checkout("D4","25000"))
            }else{
                d4.setImageResource(R.drawable.shape_rectangle_pink)
                statusD4 = true
                total += 1
                belitiket(total)

                val data = Checkout("D4","25000")
                dataList.add(data)
            }
        }

        btn_buy.setOnClickListener {
            val intent = Intent(this@PilihTempatDudukActivity, CheckOutActivity::class.java)
                .putExtra("data", dataList).putExtra("datas", data)
            startActivity(intent)
        }
    }
    private fun belitiket(total: Int){
        val btn_buy = findViewById<Button>(R.id.button8)

        if(total == 0){
            btn_buy.setText("Beli Tiket")
            btn_buy.visibility = View.INVISIBLE
        }else{
            btn_buy.setText("Beli Tiket ("+total+")")
            btn_buy.visibility = View.VISIBLE}
    }
}