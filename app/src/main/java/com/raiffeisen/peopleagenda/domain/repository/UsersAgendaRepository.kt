package com.raiffeisen.peopleagenda.domain.repository

import com.raiffeisen.peopleagenda.domain.model.User

internal interface UsersAgendaRepository {

    suspend fun getUsers(page: Int, result: Int, seed: String): List<User>
}