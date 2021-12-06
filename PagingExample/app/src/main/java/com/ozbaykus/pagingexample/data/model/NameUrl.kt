package com.ozbaykus.pagingexample.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameUrl(
    val name: String,
    val url: String
): Parcelable