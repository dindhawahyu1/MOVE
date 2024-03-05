package com.example.move.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.move.homescreen.dashboard.DasboardFragment

class Preferences(val context: Context) {
    companion object {
        const val USER_PREFF = "USER_PREFF"
    }
    val sharedPref = context.getSharedPreferences(USER_PREFF, 0)

    fun setValues(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValues(key: String): String? {
        return sharedPref.getString(key, "")
    }


}

