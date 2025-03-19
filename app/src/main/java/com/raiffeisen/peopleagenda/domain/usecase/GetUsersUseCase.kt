package com.raiffeisen.peopleagenda.domain.usecase

import com.raiffeisen.peopleagenda.common.Resource
import com.raiffeisen.peopleagenda.domain.model.User
import com.raiffeisen.peopleagenda.domain.repository.UsersAgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

internal class GetUsersUseCase(
    private val repository: UsersAgendaRepository,
) {
    operator fun invoke(
        page: Int = 0,
        result: Int = 20,
        seed: String = "abc",
    ): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getUsers(page = page, result = result, seed = seed)))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}