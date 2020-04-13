package com.example.mynewsimproved.ui.web

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.ToolbarListener
import com.example.mynewsimproved.ui.mainactivity.MainView
import kotlinx.android.synthetic.main.fragment_webview.*


class WebViewFragment : Fragment(), ToolbarListener {

    private lateinit var url: String

    private lateinit var parentView: MainView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainView) {
            parentView = context
        } else {
            throw Exception("must implement mainview interface")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            url = it.getString(ARG_URL) ?: throw Exception("url must be provided")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        swipeLayout.apply {
            isRefreshing = true
            setOnRefreshListener {
                webView.reload()
            }
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeLayout?.isRefreshing = false
            }
        }
        webView.loadUrl(url)

    }

    companion object {
        private const val ARG_URL = "com.example.mynewsimproved.ui.web.webviewFragment.arg_url"

        fun newInstance(url: String) = WebViewFragment().apply {
            arguments = bundleOf(ARG_URL to url)
        }
    }

    override fun setupToolbar() {
        parentView.setupToolbar(R.string.webview_title, R.drawable.ic_back)
    }

}
