package com.example.apptemple

import android.app.Application
import com.example.apptemple.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// класс приложения с инициализацией компонентов Koin.
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(appModule)
        }
    }
}