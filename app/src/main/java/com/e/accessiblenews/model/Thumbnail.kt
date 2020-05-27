package com.e.accessiblenews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Use the image at index position 3 - the "mediumThreeByTwo210" format
@Parcelize
data class Thumbnail (
    val url: String,
    val format: String
) : Parcelable