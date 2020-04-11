package com.example.mynewsimproved.ui.article

import com.example.mynewsimproved.ui.article.model.UiArticle

interface ArticleView {
    fun showData(articles: List<UiArticle>)
}
