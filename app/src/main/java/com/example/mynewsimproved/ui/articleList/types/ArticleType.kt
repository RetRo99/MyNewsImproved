package com.example.mynewsimproved.ui.articleList.types

sealed class ArticleType {

 object MostViewed : ArticleType()

 class TopStories(val type: String) : ArticleType()
}
