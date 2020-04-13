package com.example.mynewsimproved.ui.articleList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.ToolbarListener
import com.example.mynewsimproved.ui.articleList.adapter.ArticleAdapter
import com.example.mynewsimproved.ui.articleList.model.UiArticle
import com.example.mynewsimproved.ui.articleList.types.ArticleType
import com.example.mynewsimproved.ui.mainactivity.MainView
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(), ArticleView, ToolbarListener {

    private lateinit var storyType: String
    private var updateToolbar = false
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

            updateToolbar = it.getBoolean(UPDATE_TOOLBAR)

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
        if (updateToolbar) setupToolbar()

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

        private const val STORY_TYPE = "com.example.mynewsimproved.ui.articleList.storyType"
        private const val UPDATE_TOOLBAR = "com.example.mynewsimproved.ui.articleList.updateToolbar"

        const val TYPE_HOME = "home"
        const val TYPE_TECHNOLOGY = "technology"
        const val TYPE_ARTS = "arts"
        const val TYPE_AUTOMOBILES = "automobiles"
        const val TYPE_BOOKS = "books"
        const val TYPE_BUSINESS = "business"
        const val TYPE_FASHION = "fashion"
        const val TYPE_FOOD = "food"
        const val TYPE_HEALTH = "health"


        @JvmStatic
        fun newInstance(storyType: String = "", updateToolbar: Boolean = true) =
            ArticleFragment().apply {
                arguments = bundleOf(
                    STORY_TYPE to storyType, UPDATE_TOOLBAR to updateToolbar
                )
            }

    }

    override fun setupToolbar() {
        val titleRes = when (storyType) {
            TYPE_ARTS -> R.string.type_arts
            TYPE_AUTOMOBILES -> R.string.type_automobiles
            TYPE_BOOKS -> R.string.type_books
            TYPE_BUSINESS -> R.string.type_business
            TYPE_FASHION -> R.string.type_fashion
            TYPE_FOOD -> R.string.type_food
            TYPE_HEALTH -> R.string.type_health
            else -> throw Exception("unknown type")
        }
        parentView.setupToolbar(titleRes, R.drawable.ic_back)
    }
}
