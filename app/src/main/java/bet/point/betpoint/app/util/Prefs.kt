package bet.point.betpoint.app.util

import android.content.SharedPreferences

class Prefs(
    private val sharedPreferences: SharedPreferences
) {

    val link: String = sharedPreferences.getString(LINK, "") ?: ""
    fun setLink(link: String) {
        sharedPreferences.edit().putString(LINK, link).apply()
    }

    companion object {
        private const val LINK = "Link"
    }
}