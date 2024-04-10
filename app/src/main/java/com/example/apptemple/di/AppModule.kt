package com.example.apptemple.di

import androidx.room.Room
import com.example.apptemple.data.database.SchoolDb
import org.koin.dsl.module

// koin инициализирует базу данных и подключает ее к приложению.
val appModule = module {
    // создает один экземпляр базы данных в рамках всего приложения
    single(createdAtStart = true) {
        Room.databaseBuilder(get(), SchoolDb::class.java, SchoolDb.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}