package bet.point.betpoint.app.ui.activity.main

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.CookieManager
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import bet.point.betpoint.app.databinding.ActivityMainBinding
import bet.point.betpoint.app.ui.activity.plug.PlugActivity
import bet.point.betpoint.app.util.Prefs
import bet.point.betpoint.app.util.gone
import bet.point.betpoint.app.util.visible
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

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

    private val configSetting = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 3600
    }

    private val prefs: Prefs by lazy {
        Prefs(sharedPreferences)
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initConfigSetting()
        initBtn(savedInstanceState)
        checkLink(savedInstanceState)
    }

    private fun initConfigSetting() {
        remoteConfig.setConfigSettingsAsync(configSetting)
    }

    private fun initBtn(savedInstanceState: Bundle?) {
        requireBinding().partResult.btnTryAgain.setOnClickListener {
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
            requireBinding().webView.visible()
            requireBinding().partResult.root.gone()
            initWeb(savedInstanceState,prefs.link)
        } else {
            requireBinding().webView.gone()
            requireBinding().partResult.root.visible()
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
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val url = remoteConfig.getString(URL)
                if (url.isEmpty() || isEmulator()) {
                    startPlug()
                } else {
                    saveLink(url)
                    initWeb(savedInstanceState,url)
                }
            }
        }
    }

    private fun startPlug() {
        startActivity(Intent(this, PlugActivity::class.java))
        finish()
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


    private fun initWeb(savedInstanceState: Bundle?,url: String) {
        requireBinding().webView.webViewClient = WebViewClient()
        requireBinding().webView.settings.javaScriptEnabled = true
        if (savedInstanceState != null)
            requireBinding().webView.restoreState(savedInstanceState)
        else{
            requireBinding().webView.loadUrl(url)
        }
        requireBinding().webView.settings.domStorageEnabled = true
        requireBinding().webView.settings.javaScriptCanOpenWindowsAutomatically = true
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        with(requireBinding().webView.settings) {
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
        requireBinding().webView.saveState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun requireBinding(): ActivityMainBinding = checkNotNull(binding)
}