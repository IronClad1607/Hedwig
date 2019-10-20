package com.systemtron.finalapp.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val resultAPI = retrofit().create(ResultAPI::class.java)
}