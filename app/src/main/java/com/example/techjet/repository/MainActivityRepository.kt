package com.example.techjet.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.techjet.model.DataResponse
import com.example.techjet.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<DataResponse>()

    fun getServicesApiCall(): MutableLiveData<DataResponse> {

        val call = RetrofitClient.apiInterface.getServices("cat",1)

        call.enqueue(object: Callback<DataResponse> {
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                //Log.v("DEBUG : ", response.body().toString())

                serviceSetterGetter.value = response.body()
            }
        })

        return serviceSetterGetter
    }
}