package com.ibham.frontend.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.ibham.frontend.R

class SibiWeb : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sibi_web) // Perbarui sesuai dengan nama layout yang benar

        webView = findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true // Enable JavaScript

        webView.webChromeClient = WebChromeClient() // Optional: For handling progress bar, alerts, etc.

        // Load the URL
        webView.loadUrl("https://pmpk.kemdikbud.go.id/sibi/pencarian")
    }

    // Handle back button press to navigate in WebView history
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
