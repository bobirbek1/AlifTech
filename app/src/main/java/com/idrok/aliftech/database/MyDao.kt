package com.idrok.aliftech.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idrok.aliftech.Model


@Dao
interface MyDao {

    @Query("select * from model")
    fun getAllGuides(): LiveData<List<Model>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllGuides(list: List<Model>)

}