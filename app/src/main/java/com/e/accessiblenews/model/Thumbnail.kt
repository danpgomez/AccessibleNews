package com.e.accessiblenews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail (
    val url: String,
    val format: String,
    val caption: String
) : Parcelable