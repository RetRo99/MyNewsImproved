package com.example.mynewsoc.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mynewsimproved.ArticleModel.*
import com.example.mynewsimproved.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object NewsRepository {
    private val client = ApiClient


    private var topStories: MutableLiveData<ArrayList<TopStoryArticle>> = MutableLiveData()
    private var topTechnologyStories: MutableLiveData<ArrayList<TopStoryArticle>> = MutableLiveData()
    private var mostViewedStories: MutableLiveData<ArrayList<MostViewedArticle>> = MutableLiveData()
    private var searchedArticles: MutableLiveData<ArrayList<SearchResponse.ResponseSearch.SearchedArticle>> =
        MutableLiveData()

    init {
        loadTopStoriesArticles()
        loadMostViewedStories()
        loadTopStoriesTehnologyArticles()
    }

    fun getMostViewedStories(): MutableLiveData<ArrayList<MostViewedArticle>> {
        return mostViewedStories
    }

    fun getSearchedArticles(): MutableLiveData<ArrayList<SearchResponse.ResponseSearch.SearchedArticle>> {
        return searchedArticles
    }


    fun getTopStoriesArticles(): MutableLiveData<ArrayList<TopStoryArticle>> {
        return topStories

    }

    fun getTopStoriesTehnologyArticles(): MutableLiveData<ArrayList<TopStoryArticle>> {
        return topTechnologyStories
    }

    fun loadTopStoriesArticles() {
        val callTopStory = client.getClient.getTopStories()

        callTopStory.enqueue(object : Callback<TopStoryResponse> {
            override fun onFailure(call: Call<TopStoryResponse>, t: Throwable) {
                Log.d("onFaulur", "Error")
            }

            override fun onResponse(call: Call<TopStoryResponse>, response: Response<TopStoryResponse>) {
                if (response.isSuccessful) topStories.value = response.body()!!.results

            }
        })

    }

    fun loadTopStoriesTehnologyArticles() {
        val callTopTehnologcyStory = client.getClient.getTopTechnologyStories()

        callTopTehnologcyStory.enqueue(object : Callback<TopStoryResponse> {
            override fun onFailure(call: Call<TopStoryResponse>, t: Throwable) {
                Log.d("onFaulur", "Error")
            }

            override fun onResponse(call: Call<TopStoryResponse>, response: Response<TopStoryResponse>) {
                if (response.isSuccessful) topTechnologyStories.value = response.body()!!.results

            }
        })

    }

    fun loadMostViewedStories() {
        val callTopStory = client.getClient.getMostViewStories()
        callTopStory.enqueue(object : Callback<MostViewedResponse> {

            override fun onFailure(call: Call<MostViewedResponse>, t: Throwable) {
                Log.d("onFaulur", "Error")
            }

            override fun onResponse(call: Call<MostViewedResponse>, response: Response<MostViewedResponse>) {
                if (response.isSuccessful) mostViewedStories.value = response.body()!!.results
            }
        })


    }

    fun loadSearchedArticles(query: String, beginDate: String?, endDate: String?, sections: String) {
        val callSearchedArticles = client.getClient.getSearchedArticles(query, beginDate, endDate, sections)
        callSearchedArticles.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("onFaulur", "Error")
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) searchedArticles.value = response.body()!!.response.docs
            }
        })


    }


}