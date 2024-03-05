package com.example.move.homescreen.dashboard


import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.example.move.R
import com.example.move.databinding.FragmentDasboardBinding
import com.example.move.homescreen.model.Film
import com.example.move.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale


/**
 * A simple [Fragment] subclass.
 * Use the [DasboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DasboardFragment : Fragment() {


    private var _binding: FragmentDasboardBinding ? = null
    private val binding get() = _binding!!
    lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    lateinit var auth: FirebaseAuth


    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDasboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @RequiresApi(Build.VERSION_CODES.R)
    @Suppress("DEPRECATION")
    override fun onActivityCreated (savedInstanceState: Bundle?) {
        super.onActivityCreated (savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
        auth = FirebaseAuth.getInstance()

        val name = preferences.getValues("Nama")
        if (!name.isNullOrEmpty()){
            binding.tvNama.text = "Hello $name"
        }else{
            binding.tvNama.text = "Hello Dindha Wahyu"
        }
        if (!preferences.getValues("Saldo").equals("")) {
            currecy(preferences.getValues("Saldo")!!.toDouble(), binding.tvSaldo)
        }else{
            binding.tvSaldo.text = "Rp.1.000.000"
        }

//        Glide.with(this)
//            .load(preferences.getValues("Url"))
//            .apply (RequestOptions.circleCropTransform())
//            .into (binding.ivProfile)

        binding.rvNowplaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvComingsoon.layoutManager = LinearLayoutManager(context!!.applicationContext)

        getData ()


    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getdatasnapshot in dataSnapShot.children) {
                    val film = getdatasnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }
                if (dataList.isNotEmpty()) {
                    binding.rvNowplaying.adapter = NowplayingAdapter(dataList) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
                    binding.rvComingsoon.adapter = ComingSoonAdapter(dataList) {
                        val intent = Intent(
                            context,
                            DetailActivity::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }

}



