package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.networkcall.ApiServices
import com.example.myapplication.data.datasource.PostDataSource
import com.example.myapplication.response.ResponseModelItem
import com.example.myapplication.response.model.DetailsModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val apiService: ApiServices) : ViewModel() {

    private val _commentLiveData = MutableLiveData<DetailsModel>()
    val commentLiveData = _commentLiveData

    //pagination call using flow to emit and collect the data
    fun getPageingSingleVn(): Flow<PagingData<ResponseModelItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PostDataSource(apiService = apiService) }
        ).flow
    }

    fun setComments(detailModel:DetailsModel){
        _commentLiveData.postValue(detailModel)
    }
}