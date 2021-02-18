package com.idrok.aliftech.network


import com.idrok.aliftech.MyObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("upcomingGuides")
    fun getAllGuides(): Call<MyObject>

}