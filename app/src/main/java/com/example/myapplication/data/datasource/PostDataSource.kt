package com.example.myapplication.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.networkcall.ApiServices
import com.example.myapplication.response.ResponseModelItem
import retrofit2.HttpException
import java.io.IOException

// This is called as datasource we can get the data from this place
class PostDataSource(private val apiService: ApiServices) : PagingSource<Int, ResponseModelItem>() {
    private val PAGE_INDEX = 0
    override fun getRefreshKey(state: PagingState<Int, ResponseModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
// to load the api response
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseModelItem> {
        val position = params.key ?: PAGE_INDEX
        return try {
            val response =
                apiService.getListData()
            val result = response
            val nextKey = if (result.isEmpty()) {
                null
            } else {
                position.plus(1)
            }
            LoadResult.Page(
                data = result,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        }catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
