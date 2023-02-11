package com.example.plug.util

import android.content.SharedPreferences

class Prefs(
    private val sharedPreferences: SharedPreferences
) {

    val link: String = sharedPreferences.getString(LINK, "") ?: ""

    fun setLink(link: String) {
        sharedPreferences.edit().putString(LINK, link).apply()
    }

    companion object {
        const val LINK = "Link"
    }
}