package com.e.accessiblenews.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsObject (
    val status: String,
    val results: List<Article>,

    @Json(name = "num_results")
    val numResults: Int
) : Parcelable

