package com.example.mynewsimproved.ui.mainactivity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mynewsimproved.ui.searchResult.model.SearchParam

interface MainView {
    fun fromHomeToWeb(url: String)
    fun fromSearchToSearchResult(searchParam: SearchParam)
    fun setupToolbar(@StringRes title: Int, @DrawableRes icon: Int)
}
