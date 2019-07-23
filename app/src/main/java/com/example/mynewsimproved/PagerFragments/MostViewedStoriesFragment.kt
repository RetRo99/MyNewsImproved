package com.example.mynewsimproved.PagerFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mynewsimproved.ArticleModel.MostViewedArticle
import com.example.mynewsimproved.Pager.PagerViewModel
import com.example.mynewsimproved.R
import com.example.mynewsimproved.RecycleViewAdapters.MostViewedStoryArticleAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*


class MostViewedStoriesFragment : Fragment() {

    private lateinit var adapter: MostViewedStoryArticleAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)


        val model = ViewModelProviders.of(this).get(PagerViewModel::class.java)
        val swipeLayout = root.findViewById<SwipeRefreshLayout>(R.id.swipeLayout)
        swipeLayout.isRefreshing = true
        swipeLayout.setOnRefreshListener {
            model.loadMostViewedStories()
        }
        val observer: Observer<ArrayList<MostViewedArticle>> = Observer { topStoriesArticle ->
            if (topStoriesArticle != null) {
                linearLayoutManager = LinearLayoutManager(context)
                root.list.layoutManager = linearLayoutManager

                adapter = MostViewedStoryArticleAdapter(topStoriesArticle, context)
                root.list.adapter = adapter
                swipeLayout.isRefreshing = false

                Log.d("šta", "nalozu mostview")
            } else {
                Log.d("šta", "neki ni ok")

            }
        }
        model.getMostViewedStories().observe(this, observer)


        return root
    }

}