package com.example.project

import androidx.lifecycle.ViewModel

class CounterViewModel(favorites: Int): ViewModel() {
    private var count = favorites

    fun getCount():Int{
        return count
    }
}