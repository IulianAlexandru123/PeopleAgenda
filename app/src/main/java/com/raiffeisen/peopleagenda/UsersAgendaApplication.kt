package com.raiffeisen.peopleagenda

import android.app.Application
import com.raiffeisen.peopleagenda.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UsersAgendaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UsersAgendaApplication)
            modules(appModule)
        }
    }
}