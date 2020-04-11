package com.example.mynewsimproved.ui.article.types

sealed class ArticleType {

 object MostViewed : ArticleType()

 class TopStories(val type: String) : ArticleType()
}
