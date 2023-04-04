package bet.point.betpoint.app.ui.fragment.webview

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import bet.point.betpoint.app.databinding.FragmentWebViewBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM_URL = "url_param"


class WebViewFragment : Fragment() {

    private var binding: FragmentWebViewBinding? = null

    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null
    private var mCameraUri: Uri? = null

    private var webViewBundle: Bundle? = null

    private val featuresPermissionCamera = registerForActivityResult(
        RequestPermission(),
        ::onGotPermissionResultForCamera
    )

    private val featuresIntent = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        val results: Array<Uri> =
            if (result?.data?.data == null) {
                arrayOf(mCameraUri!!)
            } else {
                arrayOf(result.data?.data!!)
            }
        mFilePathCallback?.onReceiveValue(results)
        mFilePathCallback = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWebViewBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBtn()
    }

    override fun onPause() {
        super.onPause()
        webViewBundle = Bundle()
        requireBinding().webView.saveState(webViewBundle!!)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        requireBinding().webView.saveState(outState)
    }

    private fun onGotPermissionResultForCamera(granted: Boolean) {
        if (granted) {
            featuresIntent.launch(createChooseIntent())
        }
    }


    private fun initBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (requireBinding().webView.canGoBack()) {
                        requireBinding().webView.goBack()
                    }
                }
            })
    }

    private fun initView() {
        requireBinding().webView.webViewClient = WebViewClient()
        requireBinding().webView.webChromeClient = ChromeClient()
        requireBinding().webView.settings.javaScriptEnabled = true
        if (webViewBundle == null) {
            requireBinding().webView.loadUrl(getUrl())
        } else {
            requireBinding().webView.restoreState(webViewBundle!!)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getUrl(): String = requireArguments().getString(ARG_PARAM_URL)!!

    private fun requireBinding(): FragmentWebViewBinding = checkNotNull(binding)

    inner class ChromeClient : WebChromeClient() {
        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            if (mFilePathCallback != null) {
                mFilePathCallback!!.onReceiveValue(null)
            }
            mFilePathCallback = filePathCallback
            featuresPermissionCamera.launch(Manifest.permission.CAMERA)
            return true
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private fun createTakeImageIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        PACKAGE,
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    mCameraUri = photoURI
                }
            }
        }
    }

    private fun createSelectionIntent(): Intent {
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        return contentSelectionIntent
    }

    private fun createChooseIntent(): Intent {
        val intentArray: Array<Intent> =
            arrayOf(createTakeImageIntent())
        val chooseIntent = Intent(Intent.ACTION_CHOOSER).apply {
            putExtra(Intent.EXTRA_INTENT, createSelectionIntent())
            putExtra(Intent.EXTRA_TITLE, IMAGE_TITLE)
            putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
        }
        return chooseIntent
    }

    companion object {
        private const val IMAGE_TITLE = "Image Chooser"
        private const val PACKAGE = "bet.point.betpoint.fileprovider"

        @JvmStatic
        fun newInstance(url: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_URL, url)
                }
            }
    }
}