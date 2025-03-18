package com.raiffeisen.peopleagenda.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAgendaApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ): Call<List<String>>
}