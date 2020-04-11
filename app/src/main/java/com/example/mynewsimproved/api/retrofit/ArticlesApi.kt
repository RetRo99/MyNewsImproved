package com.example.mynewsimproved.api.retrofit

import com.example.mynewsimproved.api.response.MostViewedResponse
import com.example.mynewsimproved.api.response.SearchResponse
import com.example.mynewsimproved.api.response.TopStoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesApi {

    @GET("topstories/v2/{type}.json")
    fun getTopStories(@Path("type") type: String): Single<TopStoryResponse>

    @GET("mostpopular/v2/viewed/1.json")
    fun getMostViewStories(): Single<MostViewedResponse>

    @GET("search/v2/articlesearch.json")
    fun getSearchedArticles(
        @Query("q") query: String?,
        @Query("begin_date") beginDate: String?,
        @Query("end_date") endDate: String?,
        @Query("fq") sections: String?
    ): Single<SearchResponse>

}





