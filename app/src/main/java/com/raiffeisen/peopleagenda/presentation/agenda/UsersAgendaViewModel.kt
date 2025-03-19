package com.raiffeisen.peopleagenda.presentation.agenda

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raiffeisen.peopleagenda.common.Resource.Error
import com.raiffeisen.peopleagenda.common.Resource.Loading
import com.raiffeisen.peopleagenda.common.Resource.Success
import com.raiffeisen.peopleagenda.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val TOTAL_PAGES_TO_SHOW = 3


internal class UsersAgendaViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(UsersAgendaState())
    val state: State<UsersAgendaState> = _state

    private var currentPage = 0
    private var loadMorePagesJob: Job? = null

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Success -> {
                    _state.value = UsersAgendaState(users = result.data ?: emptyList())
                    currentPage++
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


    internal fun loadNextPage() {
        if (state.value.isLoading ||
            state.value.isPartialLoading ||
            currentPage >= TOTAL_PAGES_TO_SHOW
        ) return

        loadMorePagesJob?.cancel()
        loadMorePagesJob = getUsersUseCase(page = currentPage).onEach { result ->
            when (result) {
                is Success -> {
                    _state.value =
                        UsersAgendaState(users = _state.value.users + (result.data ?: emptyList()))
                    currentPage++
                }

                is Error -> {
                    _state.value = UsersAgendaState(
                        error = result.message ?: "Error"
                    )
                }

                is Loading -> {
                    _state.value = _state.value.copy(isPartialLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}