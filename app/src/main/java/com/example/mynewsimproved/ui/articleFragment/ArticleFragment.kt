package com.example.mynewsimproved.ui.articleFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.articleFragment.adapter.ArticleAdapter
import com.example.mynewsimproved.ui.articleFragment.model.UiArticle
import com.example.mynewsimproved.ui.articleFragment.types.ArticleType
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(), ArticleView {

    private lateinit var storyType: String
    private lateinit var sealedArticleType: ArticleType
    private lateinit var presenter: ArticleFragmentPresenter


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
        presenter = ArticleFragmentPresenter(this)


        presenter.onViewCreated(sealedArticleType)
    }

    override fun showData(articles: List<UiArticle>) {
        recycleViewArticles.adapter = ArticleAdapter(articles)
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
