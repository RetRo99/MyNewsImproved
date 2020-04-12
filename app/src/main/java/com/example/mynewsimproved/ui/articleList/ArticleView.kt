package com.example.mynewsimproved.ui.articleList

import com.example.mynewsimproved.ui.articleList.model.UiArticle

interface ArticleView {
    fun showData(articles: List<UiArticle>)
    fun showLoading()
    fun hideLoading()
}
