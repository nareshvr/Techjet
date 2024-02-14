package com.example.techjet.retrofit

import com.example.techjet.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/services/feeds/photos_public.gne?format=json")
    fun getServices(
        @Query("tags") tag: String,
        @Query("nojsoncallback") number: Int,
    ) : Call<DataResponse>

}