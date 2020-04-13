package com.example.mynewsimproved.ui.articleList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.ToolbarListener
import com.example.mynewsimproved.ui.articleList.adapter.ArticleAdapter
import com.example.mynewsimproved.ui.articleList.model.UiArticle
import com.example.mynewsimproved.ui.articleList.types.ArticleType
import com.example.mynewsimproved.ui.mainactivity.MainView
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(), ArticleView {

    private lateinit var storyType: String
    private lateinit var sealedArticleType: ArticleType
    private lateinit var presenter: ArticleFragmentPresenter
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
            storyType = it.getString(STORY_TYPE) ?: ""

            sealedArticleType = if (storyType.isNotEmpty()) {
                ArticleType.TopStories(storyType)
            } else {
                ArticleType.MostViewed

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ArticleFragmentPresenter(this, parentView)

        swipeLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
        presenter.onViewCreated(sealedArticleType)
    }

    override fun showData(articles: List<UiArticle>) {
        recycleViewArticles.adapter = ArticleAdapter(articles){
            presenter.onArticleClicked(it)
        }
    }

    override fun showLoading() {
        swipeLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeLayout.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {

        private const val STORY_TYPE = "story_type"

        @JvmStatic
        fun newInstance(storyType: String = "") =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(STORY_TYPE, storyType)
                }
            }

    }
}
