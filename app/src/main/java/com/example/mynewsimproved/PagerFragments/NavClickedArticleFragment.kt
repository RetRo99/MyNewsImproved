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
import com.example.mynewsimproved.ArticleModel.TopStoryArticle
import com.example.mynewsimproved.Pager.PagerViewModel
import com.example.mynewsimproved.R
import com.example.mynewsimproved.RecycleViewAdapters.TopStoryArticleAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*


class NavClickedArticleFragment : Fragment() {

    private lateinit var adapter: TopStoryArticleAdapter
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
            model.loadTopStoriesArticles()
        }


        val observer: Observer<ArrayList<TopStoryArticle>> = Observer { topStoriesArticle ->
            if (topStoriesArticle != null) {
                linearLayoutManager = LinearLayoutManager(context)
                root.list.layoutManager = linearLayoutManager

                adapter =
                    TopStoryArticleAdapter(topStoriesArticle, context)
                root.list.adapter = adapter
                swipeLayout.isRefreshing = false




                Log.d("šta", "nalozu top stories")
            } else {
                Log.d("šta", "neki ni ok")

            }
        }
        model.getTopStories().observe(this, observer)



        return root
    }

}