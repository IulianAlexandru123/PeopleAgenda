package com.raiffeisen.peopleagenda.domain.repository

import com.raiffeisen.peopleagenda.domain.model.User

internal interface UsersAgendaRepository {

    suspend fun getUsers(): List<User>
}