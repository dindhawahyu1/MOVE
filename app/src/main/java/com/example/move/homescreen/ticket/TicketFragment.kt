package com.example.move.homescreen.ticket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.move.databinding.FragmentTicketBinding
import com.example.move.homescreen.dashboard.ComingSoonAdapter
import com.example.move.homescreen.model.Film
import com.example.move.utils.Preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [TicketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!
    private lateinit var preference : Preferences
    lateinit var mDatabase : DatabaseReference

private var dataList =ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("UseRequireInsteadOfGet")
    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        binding.rvTiket.layoutManager = LinearLayoutManager(context!!.applicationContext)
        getData()
    }
    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapShot in dataSnapShot.getChildren()) {
                    val film =getdataSnapShot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                binding.rvTiket.adapter =ComingSoonAdapter(dataList){
                    val intent = Intent(
                        context,
                        TiketActivity::class.java
                    ).putExtra("data", it)
                    startActivity(intent)
                }

                binding.tvTotal.setText(dataList.size.toString()+" Movies")
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}