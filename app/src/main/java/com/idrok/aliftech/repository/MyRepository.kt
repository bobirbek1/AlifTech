package com.idrok.aliftech.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.idrok.aliftech.Model
import com.idrok.aliftech.MyObject
import com.idrok.aliftech.database.MyDao
import com.idrok.aliftech.network.ApiClient
import com.idrok.aliftech.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository(private val dataSource: MyDao) {

    private val TAG = "MyRepository"

    private val apiClient = ApiClient.getApiClient().create(ApiInterface::class.java)

    private var job: Job? = null


    companion object {
        private var myRepo: MyRepository? = null

        fun getInstance(dataSource: MyDao): MyRepository {

            if (myRepo != null) {
                return myRepo!!
            }

            myRepo = MyRepository(dataSource)
            return myRepo!!
        }
    }

    init {
        updateData()
    }

    fun updateData() {

        apiClient.getAllGuides().enqueue(object : Callback<MyObject> {

            override fun onResponse(call: Call<MyObject>?, response: Response<MyObject>?) {
                if (response != null && response.isSuccessful && response.body() != null) {
                    job = CoroutineScope(IO).launch {
                        dataSource.saveAllGuides(response.body().data)
                    }
                } else {
                    Log.d(TAG, "onResponse: response was not success")
                }
            }

            override fun onFailure(call: Call<MyObject>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ${t?.message}")
            }

        })
    }

    fun getAllGuides(): LiveData<List<Model>> {
        return dataSource.getAllGuides()
    }

    fun onDestroy() {
        job?.cancel()
    }

}