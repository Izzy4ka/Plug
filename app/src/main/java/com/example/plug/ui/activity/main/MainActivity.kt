package com.example.plug.ui.activity.main

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.telephony.TelephonyManager
import android.webkit.CookieManager
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.plug.databinding.ActivityMainBinding
import com.example.plug.ui.activity.plug.PlugActivity
import com.example.plug.util.Prefs
import com.example.plug.util.gone
import com.example.plug.util.visible
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig

const val APP_PREFERENCES = "App_preferences"

const val URL = "url"


class MainActivity : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            APP_PREFERENCES,
            Application.MODE_PRIVATE
        )
    }
    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    private val prefs: Prefs by lazy {
        Prefs(sharedPreferences)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBtn(savedInstanceState)
        checkLink(savedInstanceState)
    }

    private fun initBtn(savedInstanceState: Bundle?) {
        binding.partResult.btnTryAgain.setOnClickListener {
            checkInternet(savedInstanceState)
        }
    }

    private fun checkLink(savedInstanceState: Bundle?) {
        if (prefs.link.isEmpty()) {
            initFirebaseConfig(savedInstanceState)
        } else {
            checkInternet(savedInstanceState)
        }
    }

    private fun checkInternet(savedInstanceState: Bundle?) {
        if (isInternetAvailable()) {
            binding.webView.visible()
            binding.partResult.root.gone()
            initWeb(savedInstanceState)
        } else {
            binding.webView.gone()
            binding.partResult.root.visible()
        }
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    private fun initFirebaseConfig(savedInstanceState: Bundle?) {
        val url = remoteConfig.getString(URL)
        if (url.isEmpty() || isEmulator() || isHaveSimCard()) {
            startActivity(Intent(this, PlugActivity::class.java))
        } else {
            saveLink(url)
            initWeb(savedInstanceState)
        }
    }

    private fun saveLink(url: String) {
        prefs.setLink(url)
    }

    private fun isEmulator(): Boolean =
        (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("sdk_gphone64_arm64")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator"))


    private fun isHaveSimCard(): Boolean =
        TelephonyManager.SIM_STATE_ABSENT !=
                (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simState

    private fun initWeb(savedInstanceState: Bundle?) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        if (savedInstanceState != null)
            binding.webView.restoreState(savedInstanceState)
        else
            binding.webView.loadUrl(prefs.link)
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        with(binding.webView.settings) {
            loadWithOverviewMode = true
            domStorageEnabled = true
            useWideViewPort = true
            databaseEnabled = true
            setSupportZoom(false)
            allowFileAccess = true
            allowContentAccess = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        binding.webView.saveState(outState)
    }
}