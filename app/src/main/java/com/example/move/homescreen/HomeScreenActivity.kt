package com.example.move.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.move.R
import com.example.move.databinding.ActivityHomeScreenBinding
import com.example.move.homescreen.ticket.TicketFragment
import com.example.move.homescreen.dashboard.DasboardFragment

class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentHome = DasboardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentSetting = SettingFragment()

        setFragment(fragmentHome)
        setFragment(fragmentTicket)
        setFragment(fragmentSetting)

        binding.ivMenu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIconView(binding.ivMenu1, R.drawable.ic_home_active)
            changeIconView(binding.ivMenu2, R.drawable.ic_tiket)
            changeIconView(binding.ivMenu3, R.drawable.ic_profile)
        }
        binding.ivMenu2.setOnClickListener {
            setFragment(fragmentTicket)

            changeIconView(binding.ivMenu1, R.drawable.ic_home)
            changeIconView(binding.ivMenu2, R.drawable.ic_tiket_active)
            changeIconView(binding.ivMenu3, R.drawable.ic_profile)
        }
        binding.ivMenu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIconView(binding.ivMenu1, R.drawable.ic_home)
            changeIconView(binding.ivMenu2, R.drawable.ic_tiket)
            changeIconView(binding.ivMenu3, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIconView(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}