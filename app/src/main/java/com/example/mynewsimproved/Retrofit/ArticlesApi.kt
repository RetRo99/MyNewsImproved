package com.example.mynewsimproved.Retrofit

import com.example.mynewsimproved.ArticleModel.MostViewedResponse
import com.example.mynewsimproved.ArticleModel.SearchResponse
import com.example.mynewsimproved.ArticleModel.TopStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("topstories/v2/home.json")
    fun getTopStories(): Call<TopStoryResponse>

    @GET("mostpopular/v2/viewed/1.json")
    fun getMostViewStories(): Call<MostViewedResponse>

    @GET("topstories/v2/technology.json")
    fun getTopTechnologyStories(): Call<TopStoryResponse>

    @GET("search/v2/articlesearch.json")
    fun getSearchedArticles(
        @Query("q") query: String?,
        @Query("begin_date") beginDate: String?,
        @Query("end_date") endDate: String?,
        @Query("fq") sections: String?
    ): Call<SearchResponse>




}





