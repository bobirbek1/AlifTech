package com.idrok.aliftech

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model")
data class Model(
    @PrimaryKey
    val url: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val name: String = "",
    val icon: String = "",
    val objType: String = "",
    val loginRequired: Boolean? = null
)

data class MyObject(
    val data: List<Model> = listOf(),
    val total: String = ""
)