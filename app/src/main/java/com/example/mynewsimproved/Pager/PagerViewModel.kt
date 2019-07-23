package com.example.mynewsimproved.Pager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mynewsimproved.ArticleModel.MostViewedArticle
import com.example.mynewsimproved.ArticleModel.TopStoryArticle
import com.example.mynewsoc.Repository.NewsRepository

class PagerViewModel(application: Application) : AndroidViewModel(application) {



    private val repo = NewsRepository


     fun getTopStories():MutableLiveData<ArrayList<TopStoryArticle>>{
        return repo.getTopStoriesArticles()
    }
     fun getMostViewedStories():MutableLiveData<ArrayList<MostViewedArticle>>{
        return repo.getMostViewedStories()
    }

    fun getTopTehnologcyStories():MutableLiveData<ArrayList<TopStoryArticle>>{
        return repo.getTopStoriesTehnologyArticles()
    }


    fun loadTopStoriesArticles(){
        repo.loadTopStoriesArticles()
    }

    fun loadTopStoriesTehnologyArticles(){
        repo.loadTopStoriesTehnologyArticles()
    }

    fun loadMostViewedStories(){
        repo.loadMostViewedStories()
    }
}