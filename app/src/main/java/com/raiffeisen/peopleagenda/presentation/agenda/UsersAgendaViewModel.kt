package com.raiffeisen.peopleagenda.presentation.agenda

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raiffeisen.peopleagenda.common.Resource.Error
import com.raiffeisen.peopleagenda.common.Resource.Loading
import com.raiffeisen.peopleagenda.common.Resource.Success
import com.raiffeisen.peopleagenda.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class UsersAgendaViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(UsersAgendaState())
    val state: State<UsersAgendaState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Success -> {
                    _state.value = UsersAgendaState(users = result.data ?: emptyList())
                }

                is Error -> {
                    _state.value = UsersAgendaState(
                        error = result.message ?: "Error"
                    )
                }

                is Loading -> {
                    _state.value = UsersAgendaState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}