package com.ozbaykus.pagingexample.api

import com.ozbaykus.pagingexample.data.model.Character
import com.ozbaykus.pagingexample.data.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): PagedResponse<Character>
}