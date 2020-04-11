package com.example.mynewsimproved.api.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(val response: ResponseSearch) {
    data class ResponseSearch(val docs: ArrayList<SearchedArticle>) {
        data class SearchedArticle(
            @SerializedName("pub_date")val published_date: String, @SerializedName("web_url") val url: String,
            val news_desk: String,
            val abstract: String,
            val multimedia: ArrayList<SearchMedia>,
            @SerializedName("section_name") val section :String,
            @SerializedName("subsection_name") val subsection :String
        ) {
            data class SearchMedia(val url: String)


        }


    }

}
