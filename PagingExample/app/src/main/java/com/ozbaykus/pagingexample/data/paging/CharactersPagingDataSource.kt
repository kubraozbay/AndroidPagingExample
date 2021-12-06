package com.ozbaykus.pagingexample.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozbaykus.pagingexample.util.STARTING_PAGE_INDEX
import com.ozbaykus.pagingexample.api.ApiService
import com.ozbaykus.pagingexample.data.model.Character
import retrofit2.HttpException
import java.io.IOException

class CharactersPagingDataSource(
    private val apiService: ApiService
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getAllCharacters(page)
            var nextPageNumber: Int? = null
            if (response.pageInfo.next != null) {
                val uri = Uri.parse(response.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

}