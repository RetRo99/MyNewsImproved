package com.example.mynewsimproved.ui.mainactivity

import com.example.mynewsimproved.ui.searchResult.model.SearchParam

interface MainView {
    fun fromHomeToWeb(url: String)
    fun fromSearchToSearchResult(searchParam: SearchParam)
}
