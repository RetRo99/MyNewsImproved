package com.example.mynewsimproved.api.response

import com.google.gson.annotations.SerializedName


data class TopStoryResponse(val results: ArrayList<TopStoryArticle>)

data class TopStoryArticle(
    val abstract: String,
    val published_date: String,
    val section: String,
    val subsection: String,
    @SerializedName("short_url") val url: String,
    val multimedia: ArrayList<TopStoryMultimedia>
)

class TopStoryMultimedia(val url: String) {

}

