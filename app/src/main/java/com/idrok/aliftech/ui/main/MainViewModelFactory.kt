package com.idrok.aliftech.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idrok.aliftech.database.MyDao

class MainViewModelFactory(private val dataSource: MyDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource) as T
        } else {
            throw IllegalArgumentException("MainViewModel class can not find")
        }
    }
}