package com.example.mynewsimproved.api.mapper

import com.example.mynewsimproved.api.response.MostViewedResponse
import com.example.mynewsimproved.api.response.SearchResponse
import com.example.mynewsimproved.api.response.TopStoryArticle
import com.example.mynewsimproved.ui.articleList.model.UiArticle

class RestaurantMapper {
    fun mapSearchResultToUi(searchedArticles: ArrayList<SearchResponse.ResponseSearch.SearchedArticle>): List<UiArticle> {
        return searchedArticles.map {
            val photoUrl =
                if (it.multimedia.size != 0) "https://static01.nyt.com/${it.multimedia[0].url}" else ""
            UiArticle(
                it.abstract,
                it.published_date.subSequence(0, 10).toString(),
                it.section.capitalize(),
                it.url,
                photoUrl
            )
        }

    }

    fun mapTopStoriesToUi(results: ArrayList<TopStoryArticle>): List<UiArticle> {
        return results.map {
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

    fun mapMostViewToUi(mostViewedResponse: MostViewedResponse): List<UiArticle> {
        return mostViewedResponse.results.map {

            val photoUrl = if (it.media.size != 0) {
                if (it.media[0].mostViewedMetaData.size != 0) it.media[0].mostViewedMetaData[0].url else ""
            } else ""

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
