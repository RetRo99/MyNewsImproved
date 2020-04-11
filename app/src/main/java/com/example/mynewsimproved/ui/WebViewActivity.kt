package com.example.mynewsimproved.ui

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_web_view_activity.*
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mynewsimproved.R


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_activity)

        val url = intent.getStringExtra("WEBSITE_ADDRESS")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeLayout.isRefreshing = false
            }
        }


        webView.loadUrl(url)




        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar?>(R.id.activity_toolbar)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val swipeLayout = findViewById<SwipeRefreshLayout>(R.id.swipeLayout)
        swipeLayout.isRefreshing = true
        swipeLayout.setOnRefreshListener {
            webView.reload()
        }





    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
