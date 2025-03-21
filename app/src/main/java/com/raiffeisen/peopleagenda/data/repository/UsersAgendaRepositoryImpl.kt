package com.raiffeisen.peopleagenda.data.repository

import com.raiffeisen.peopleagenda.data.remote.UsersAgendaApi
import com.raiffeisen.peopleagenda.domain.model.User
import com.raiffeisen.peopleagenda.domain.repository.UsersAgendaRepository

internal class UsersAgendaRepositoryImpl(
    private val api: UsersAgendaApi,
) : UsersAgendaRepository {

    override suspend fun getUsers(page: Int, result: Int, seed: String): List<User> {
        return api.getUsers(
            page = page,
            results = result,
            seed = seed,
        ).results.map { user -> user.toDomain() }
    }

}