package com.systemtron.finalapp.networks

import com.systemtron.finalapp.modals.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultAPI {

    @GET("/customsearch/v1?key=AIzaSyAOxIXzmmJMuRnPaAr-i20VVAOSMIcgKAs&cx=005958057711242720279:nljncz1caus")
    suspend fun getResults(@Query("q") q: String, @Query("start") SI: Int): Response<Data>


}