package com.example.mynewsimproved.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsimproved.R
import com.example.mynewsimproved.api.response.SearchResponse
import kotlinx.android.synthetic.main.activity_search_result.*


class SearchResult : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val query = intent.getStringExtra("query")
//        val startDateConvertedString = intent.getStringExtra("startDateConvertedString")
//        val endDateConvertedString = intent.getStringExtra("endDateConvertedString")
//        val sections = intent.getStringExtra("sections")
//
//
//
//        val swipeLayout = findViewById<SwipeRefreshLayout1>(R.id.swipeLayout)
//        swipeLayout.isRefreshing = true
//        swipeLayout.setOnRefreshListener {
//            model.loadSearchedArticles(
//                query,
//                startDateConvertedString,
//                endDateConvertedString,
//                sections
//            )
//        }
//
//        val observer: Observer<ArrayList<SearchResponse.ResponseSearch.SearchedArticle>> =
//            Observer { searchedArticles ->
//                if (searchedArticles != null) {
//                    if (searchedArticles.size != 0) {
//                        linearLayoutManager = LinearLayoutManager(this)
//                        recyclerViewSearch.layoutManager = linearLayoutManager
//
//                        adapter = SearchArticleAdapter(searchedArticles, this)
//                        recyclerViewSearch.adapter = adapter
//                        swipeLayout.isRefreshing = false
//
//
//
//                        Log.d("šta", "nalozu top stories")
//                    } else {
//                        AlertDialog.Builder(this)
//                            .setTitle("No results")
//                            .setMessage("Try searching with different parameters")
//
//                            // Specifying a listener allows you to take an action before dismissing the dialog.
//                            // The dialog is automatically dismissed when a dialog button is clicked.
//                            .setPositiveButton(android.R.string.yes) { dialog, _ ->
//                                finish()
//                            }
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show()
//                        Log.d("šta", "neki ni ok")
//
//                    }
//                }
//            }
//
//        mToolbar?.setNavigationOnClickListener {
//            finish()
//        }
//
//    }
//
//
//    override fun onPause() {
//        super.onPause()
//        finish()
    }
}
