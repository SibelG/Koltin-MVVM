package com.app.koltinpoc.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
):Parcelable