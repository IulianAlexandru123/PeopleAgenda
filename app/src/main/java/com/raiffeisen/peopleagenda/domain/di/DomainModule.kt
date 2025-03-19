package com.raiffeisen.peopleagenda.domain.di

import com.raiffeisen.peopleagenda.domain.usecase.GetUsersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val domainModule = module {
    factoryOf(::GetUsersUseCase)
}