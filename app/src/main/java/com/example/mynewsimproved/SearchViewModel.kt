package com.example.mynewsimproved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mynewsimproved.ArticleModel.SearchResponse
import com.example.mynewsimproved.Repository.NewsRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {



    private val repo = NewsRepository



    fun loadSearchedArticles(query:String,  beginDate:String?,  endDate:String?,  sections:String){
        repo.loadSearchedArticles(query,beginDate,endDate,sections)
    }

    fun getSearchedArticles():MutableLiveData<ArrayList<SearchResponse.ResponseSearch.SearchedArticle>>?{
        return repo.getSearchedArticles()
    }

    fun setSearchedArticles(){
        repo.setSearchedArticles()
    }

}