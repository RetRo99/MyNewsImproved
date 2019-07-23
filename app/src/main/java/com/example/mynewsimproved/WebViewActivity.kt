package com.example.mynewsimproved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_web_view_activity.*
import android.webkit.WebViewClient


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_activity)

        val url = intent.getStringExtra("WEBSITE_ADDRESS")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar?>(R.id.activity_toolbar)

        setSupportActionBar(mToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }

}
