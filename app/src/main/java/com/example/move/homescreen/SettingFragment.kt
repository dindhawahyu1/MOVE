package com.example.move.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.move.R
import com.example.move.databinding.FragmentSettingBinding
import com.example.move.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    private var _binding : FragmentSettingBinding ? = null
    private val binding get() = _binding!!
    lateinit var preferences : Preferences
    lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)

        val nama = preferences.getValues("Nama")
        val email = preferences.getValues("Email")

        if (!nama.isNullOrEmpty()) {
            binding!!.textView54.text = nama
        }else{
            binding.textView54.text = "Dindha Wahyu"
        }
        if (!email.isNullOrEmpty()) {
            binding.textView55.text = email
        }else{
            binding.textView55.text = "dindhawahyu1@gmail.com"
        }

        val pic = binding.imageView7
//        if (!preferences.getValues("Url").isNullOrEmpty()) {
//            pic.setImageURI(preferences.getValues("Url")!!)
//        }else{
//            pic.setImageResource(R.drawable.user_pic)
//        }

        Glide.with(this)
            .load(pic)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imageView7)
    }




}