package com.raiffeisen.peopleagenda.data.remote

import com.raiffeisen.peopleagenda.data.remote.dto.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UsersAgendaApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ): UsersResponseDto
}