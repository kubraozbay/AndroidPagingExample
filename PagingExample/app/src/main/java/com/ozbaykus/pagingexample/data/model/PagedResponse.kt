package com.ozbaykus.pagingexample.data.model

import com.google.gson.annotations.SerializedName

data class PagedResponse<T>(
    @SerializedName("info")
    val pageInfo: PageInfo,
    @SerializedName("results")
    val results: List<T> = listOf()
)

