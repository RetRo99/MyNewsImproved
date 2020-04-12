package com.example.mynewsimproved.ui.searchResult

import com.example.mynewsimproved.ui.articleList.model.UiArticle

interface SearchResultView {
    fun showLoading()
    fun hideLoading()
    fun showData(articles: List<UiArticle>)
    fun showNoResultDialog()
}
