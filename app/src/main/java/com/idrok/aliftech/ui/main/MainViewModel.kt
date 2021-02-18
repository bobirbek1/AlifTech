package com.idrok.aliftech.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idrok.aliftech.Model
import com.idrok.aliftech.database.MyDao
import com.idrok.aliftech.repository.MyRepository

class MainViewModel(dataSource: MyDao) : ViewModel() {

    private val repo = MyRepository.getInstance(dataSource)

    fun getAllGuides(): LiveData<List<Model>> {
        return repo.getAllGuides()
    }

    fun updateData() {
        repo.updateData()
    }

    override fun onCleared() {
        super.onCleared()
        repo.onDestroy()
    }

}