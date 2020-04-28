package com.example.mynewsimproved.api.repository

import com.example.mynewsimproved.api.mapper.RestaurantMapper
import com.example.mynewsimproved.api.response.MostViewedResponse
import com.example.mynewsimproved.api.response.SearchResponse
import com.example.mynewsimproved.api.response.TopStoryResponse
import com.example.mynewsimproved.api.retrofit.ApiClient
import com.example.mynewsimproved.ui.articleList.model.UiArticle
import com.example.mynewsimproved.ui.articleList.types.ArticleType
import com.example.mynewsimproved.ui.searchResult.model.SearchParam
import io.reactivex.Single


object NewsRepository {
    private val client = ApiClient.getClient
    private val mapper : RestaurantMapper = RestaurantMapper()

    private fun getMostViewArticles(): Single<List<UiArticle>> {
        return client.getMostViewStories()
            .map {
                mapper.mapMostViewToUi(it)
            }


    }

    fun loadSearchedArticles(
        params: SearchParam
    ): Single<List<UiArticle>> {
        return client.getSearchedArticles(
            params.query,
            params.startDate,
            params.endDate,
            params.sections
        )
            .map {
                mapper.mapSearchResultToUi(it.response.docs)
            }

    }

    fun loadArticles(articleType: ArticleType): Single<List<UiArticle>> {
        return when (articleType) {
            is ArticleType.MostViewed -> getMostViewArticles()
            is ArticleType.TopStories -> getTopStories(articleType.type)

        }

    }

    private fun getTopStories(type: String): Single<List<UiArticle>> {
        return client.getTopStories(type).map {
            mapper.mapTopStoriesToUi(it.results)
        }
    }




}
