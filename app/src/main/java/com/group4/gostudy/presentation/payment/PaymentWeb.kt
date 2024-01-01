package com.group4.gostudy.presentation.payment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityPaymentWebBinding
import com.group4.gostudy.presentation.detail.DetailCourseActivity
import com.group4.gostudy.presentation.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PaymentWeb : AppCompatActivity() {
    private val binding: ActivityPaymentWebBinding by lazy {
        ActivityPaymentWebBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailViewModel by viewModel { parametersOf(intent.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val `in`: Intent = getIntent()
        val url: String = `in`.getStringExtra("URL").toString()
        openUrlFromWebView(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openUrlFromWebView(url: String) {
        val webView: WebView = findViewById(R.id.myWebView)
        webView.setWebViewClient(object : WebViewClient() {
            val pd: ProgressDialog = ProgressDialog(this@PaymentWeb)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest
            ): Boolean {
                val requestUrl: String = request.getUrl().toString()
                return if (requestUrl.contains("gojek://") ||
                    requestUrl.contains("shopeeid://") ||
                    requestUrl.contains("//wsa.wallet.airpay.co.id/") || // This is handle for sandbox Simulator
                    requestUrl.contains("/gopay/partner/") ||
                    requestUrl.contains("/shopeepay/")
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, request.getUrl())
                    startActivity(intent)
                    // `true` means for the specified url, will be handled by OS by starting Intent
                    true
                } else if (requestUrl.contains("example.com/")) {
                    DetailCourseActivity.startActivity(this@PaymentWeb, viewModel.course)
                    true
                } else {
                    false
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                pd.setMessage("loading")
                pd.show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pd.hide()
                super.onPageFinished(view, url)
            }
        })
        webView.getSettings().setLoadsImagesAutomatically(true)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        webView.loadUrl(url)
    }
}
