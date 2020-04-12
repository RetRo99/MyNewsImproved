package com.example.mynewsimproved.ui.searchResult

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mynewsimproved.R
import com.example.mynewsimproved.ui.articleList.adapter.ArticleAdapter
import com.example.mynewsimproved.ui.articleList.model.UiArticle
import com.example.mynewsimproved.ui.mainactivity.MainView
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import kotlinx.android.synthetic.main.fragment_search_result.*


class SearchResult : Fragment(), SearchResultView {

    private lateinit var param: SearchParam
    private lateinit var presenter: SearchResultPresenter

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
            param = it.getParcelable(ARG_SEARCH_PARAM) ?: throw Exception("params must be provided")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

        presenter = SearchResultPresenter(this, parentView)

        swipeLayout.setOnRefreshListener {
            presenter.onRefresh()
        }


        presenter.onViewCreated(param)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


    companion object {
        private const val ARG_SEARCH_PARAM =
            "com.example.mynewsimproved.ui.searchResult.argSearchParam"

        fun newInstance(params: SearchParam) = SearchResult().apply {
            arguments = bundleOf(ARG_SEARCH_PARAM to params)
        }
    }

    override fun showLoading() {
        swipeLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeLayout.isRefreshing = false
    }

    override fun showData(articles: List<UiArticle>) {
        recyclerViewSearch.adapter = ArticleAdapter(articles) {
            presenter.onArticleClicked(it)
        }
    }

    override fun showNoResultDialog() {

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.no_results))
            .setMessage(getString(R.string.try_again_different_params))

            .setPositiveButton(android.R.string.yes) { dialog, _ ->
                //
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()    }

}
