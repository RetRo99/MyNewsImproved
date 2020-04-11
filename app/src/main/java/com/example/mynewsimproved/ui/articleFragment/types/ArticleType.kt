package com.example.mynewsimproved.ui.articleFragment.types

sealed class ArticleType {

 object MostViewed : ArticleType()

 class TopStories(val type: String) : ArticleType()
}
