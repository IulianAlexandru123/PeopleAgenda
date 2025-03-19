package com.raiffeisen.peopleagenda.data.di

import com.raiffeisen.peopleagenda.data.remote.UsersAgendaApi
import com.raiffeisen.peopleagenda.data.repository.UsersAgendaRepositoryImpl
import com.raiffeisen.peopleagenda.domain.repository.UsersAgendaRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {
    single { get<Retrofit>().create(UsersAgendaApi::class.java) }
    singleOf(::UsersAgendaRepositoryImpl)
    single<UsersAgendaRepository> { UsersAgendaRepositoryImpl(get()) }
}