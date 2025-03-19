package com.raiffeisen.peopleagenda.presentation.agenda

import com.raiffeisen.peopleagenda.domain.model.User

internal data class UsersAgendaState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)