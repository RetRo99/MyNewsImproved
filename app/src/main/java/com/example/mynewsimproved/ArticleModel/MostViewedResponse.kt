package com.example.mynewsimproved.ArticleModel

import com.google.gson.annotations.SerializedName

data class MostViewedResponse(val results:ArrayList<MostViewedArticle>)

data class MostViewedArticle(
    val abstract: String,
    val published_date: String,
    val section: String,
    val url: String,
    val media: ArrayList<MostViewedMedia>)



data class MostViewedMedia(@SerializedName("media-metadata")val mostViewedMetaData: ArrayList<MostViewedMetaData>)




data class MostViewedMetaData(val url:String)


