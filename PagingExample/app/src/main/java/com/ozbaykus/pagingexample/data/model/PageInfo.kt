package com.ozbaykus.pagingexample.data.model

import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)