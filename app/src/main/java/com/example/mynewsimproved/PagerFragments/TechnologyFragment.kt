package com.example.mynewsimproved.PagerFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mynewsimproved.ArticleModel.TopStoryArticle
import com.example.mynewsimproved.Pager.PagerViewModel
import com.example.mynewsimproved.R
import com.example.mynewsimproved.RecycleViewAdapters.TopStoryArticleAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [TechnologyFragment.OnListFragmentInteractionListener] interface.
 */
class TechnologyFragment : Fragment() {

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
            model.loadTopStoriesTehnologyArticles()
        }
        val observer: Observer<ArrayList<TopStoryArticle>> = Observer { topStoriesArticle ->
            if (topStoriesArticle != null) {
                linearLayoutManager = LinearLayoutManager(context)
                root.list.layoutManager = linearLayoutManager

                adapter = TopStoryArticleAdapter(topStoriesArticle, context)
                root.list.adapter = adapter
                swipeLayout.isRefreshing = false

                Log.d("šta", "nalozu top tehnologija stories")
            } else {
                Log.d("šta", "neki ni ok")

            }
        }
        model.getTopTehnologcyStories().observe(this, observer)



        return root
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
}
