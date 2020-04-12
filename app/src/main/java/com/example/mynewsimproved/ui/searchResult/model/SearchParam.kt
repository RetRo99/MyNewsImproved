package com.example.mynewsimproved.ui.searchResult.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchParam(
    val query: String,
    val startDate: String?,
    val endDate: String?,
    val sections: String
) : Parcelable
