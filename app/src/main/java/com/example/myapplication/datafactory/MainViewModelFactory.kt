package com.example.myapplication.datafactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.networkcall.ApiServices
import com.example.myapplication.viewmodel.HomeViewModel

// To create a view model instance we need this factory class
class MainViewModelFactory(private val apiService: ApiServices) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}