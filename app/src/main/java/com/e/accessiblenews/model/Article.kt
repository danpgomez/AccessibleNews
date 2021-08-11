package com.e.accessiblenews.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Article (
    val title: String,
    val url: String,

    @Json(name = "abstract")
    val excerpt: String,

    @Json(name = "published_date")
    val publishedDate: String,

    @Json(name = "multimedia")
    val imageURLs: List<Thumbnail>?
) : Parcelable
