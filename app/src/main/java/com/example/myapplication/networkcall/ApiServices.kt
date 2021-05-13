package com.example.myapplication.networkcall

import com.example.myapplication.response.ResponseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiServices {

    //API services
    @GET("/repositories")
    suspend fun getListData(): ResponseModel

    //Retrofit instance creater
    companion object {
        fun getApiService() = Retrofit.Builder()
            .baseUrl(UrlHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}