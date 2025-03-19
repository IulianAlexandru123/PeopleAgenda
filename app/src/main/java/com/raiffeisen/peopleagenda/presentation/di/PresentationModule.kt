package com.raiffeisen.peopleagenda.presentation.di

import com.raiffeisen.peopleagenda.presentation.agenda.UsersAgendaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


internal val presentationModule = module {
    viewModelOf(::UsersAgendaViewModel)
}