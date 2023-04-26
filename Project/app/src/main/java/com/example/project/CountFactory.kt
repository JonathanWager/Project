package com.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountFactory(private val favoriteCount:Int):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)){
            return CounterViewModel(favoriteCount) as T
        }
        throw java.lang.IllegalArgumentException("Uknown View Model Class")
    }
}