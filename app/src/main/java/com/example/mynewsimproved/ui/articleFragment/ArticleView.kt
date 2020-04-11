package com.example.mynewsimproved.ui.articleFragment

import com.example.mynewsimproved.ui.articleFragment.model.UiArticle

interface ArticleView {
    fun showData(articles: List<UiArticle>)
}
