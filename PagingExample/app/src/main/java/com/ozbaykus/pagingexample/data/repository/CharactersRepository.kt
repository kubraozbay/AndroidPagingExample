package com.ozbaykus.pagingexample.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ozbaykus.pagingexample.api.ApiService
import com.ozbaykus.pagingexample.data.model.Character
import com.ozbaykus.pagingexample.data.paging.CharactersPagingDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(
    private val service: ApiService
) {

    fun getCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                CharactersPagingDataSource(service)
            }
        ).liveData
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 1
    }
}