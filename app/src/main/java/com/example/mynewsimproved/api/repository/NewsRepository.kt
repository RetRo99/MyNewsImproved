package com.example.mynewsimproved.api.repository

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

    private fun getMostViewArticles(): Single<List<UiArticle>> {
        return client.getMostViewStories()
            .map {
                mapMostViewToUi(it)
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
                mapSearchResultToUi(it)
            }

    }

    private fun mapSearchResultToUi(searchResponse: SearchResponse): List<UiArticle> {
        return searchResponse.response.docs.map {
            val photoUrl = if (it.multimedia.size != 0) "https://static01.nyt.com/${it.multimedia[0].url}" else ""
            UiArticle(
                it.abstract,
                it.published_date.subSequence(0, 10).toString(),
                it.section.capitalize(),
                it.url,
                photoUrl
            )
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
            mapTopStoriesToUi(it)
        }
    }

    private fun mapTopStoriesToUi(topStoryResponse: TopStoryResponse): List<UiArticle> {
        return topStoryResponse.results.map {
            val photoUrl = if (it.multimedia.size != 0) it.multimedia[0].url else ""
            UiArticle(
                it.abstract,
                it.published_date.subSequence(0, 10).toString(),
                it.section.capitalize(),
                it.url,
                photoUrl
            )
        }
    }

    private fun mapMostViewToUi(mostViewedResponse: MostViewedResponse): List<UiArticle> {

        return mostViewedResponse.results.map {

            val photoUrl = if(it.media.size != 0) {
                if (it.media[0].mostViewedMetaData.size != 0) it.media[0].mostViewedMetaData[0].url else ""
            }else ""

            UiArticle(
                it.abstract,
                it.published_date.subSequence(0, 10).toString(),
                it.section.capitalize(),
                it.url,
                photoUrl
            )
        }
    }


}
