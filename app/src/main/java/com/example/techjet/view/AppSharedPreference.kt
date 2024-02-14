package com.example.techjet.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.techjet.model.DataResponse
import com.example.techjet.model.Item
import com.google.gson.Gson


object AppSharedPreference {
    const val SHARED_PREFERENCES_NAME = "Sample"
    private lateinit var sharedPreferences: SharedPreferences
    const val DATA_OBJECT = "data_data_object"
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    }

    fun saveResponse(userData: DataResponse) {
        val editor = sharedPreferences.edit()
        editor.putString(
            DATA_OBJECT,
            Gson().toJson(userData)
        )
        editor.commit()
    }

    fun getResponse(): DataResponse {
        val userData = sharedPreferences.getString(DATA_OBJECT, null)
        return if (userData != null)
            Gson().fromJson(userData, DataResponse::class.java)
        else
            DataResponse("","",emptyList<Item>(),"", "","")
    }



}