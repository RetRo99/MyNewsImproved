package com.example.mynewsimproved

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsimproved.ArticleModel.SearchResponse
import com.example.mynewsimproved.RecycleViewAdapters.SearchArticleAdapter
import kotlinx.android.synthetic.main.activity_search_result.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout as SwipeRefreshLayout1
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog





@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SearchResult : AppCompatActivity() {
    private lateinit var adapter: SearchArticleAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar?>(R.id.activity_toolbar)

        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        val query =intent.getStringExtra("query")
       val  startDateConvertedString=intent.getStringExtra("startDateConvertedString")
        val endDateConvertedString =intent.getStringExtra("endDateConvertedString")
        val sections=intent.getStringExtra("sections")



        val model = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val swipeLayout = findViewById<SwipeRefreshLayout1>(R.id.swipeLayout)
        swipeLayout.isRefreshing = true
        swipeLayout.setOnRefreshListener {
            model.loadSearchedArticles(query,startDateConvertedString,endDateConvertedString,sections)
        }

        val observer: Observer<ArrayList<SearchResponse.ResponseSearch.SearchedArticle>> =
            Observer { searchedArticles ->
                if (searchedArticles != null) {
                    if (searchedArticles.size != 0) {
                        linearLayoutManager = LinearLayoutManager(this)
                        recyclerViewSearch.layoutManager = linearLayoutManager

                        adapter = SearchArticleAdapter(searchedArticles, this)
                        recyclerViewSearch.adapter = adapter
                        swipeLayout.isRefreshing = false





                        Log.d("šta", "nalozu top stories")
                    } else {
                        AlertDialog.Builder(this)
                            .setTitle("No results")
                            .setMessage("Try searching with different parameters")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes) { dialog, _ ->
                                model.setSearchedArticles()
                                finish()
                            }
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()
                        Log.d("šta", "neki ni ok")

                    }
                }
            }
        model.getSearchedArticles()?.observe(this, observer)

        mToolbar?.setNavigationOnClickListener{
            finish()
            model.setSearchedArticles()
        }

    }


    override fun onPause() {
        super.onPause()
        finish()
    }
}
