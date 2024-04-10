package com.example.apptemple.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

// означает, что этот класс является базой данных
@Database(
    // список сущностей (таблиц), которые будут в базе данных
    entities = [User::class, Event::class, Article::class],
    // версия базы данных
    version = 1,
    // надо ли экспортировать схему. В нашем случае нет
    exportSchema = false
)
abstract class SchoolDb : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun eventDao() : EventDao
    abstract fun articleDao() : ArticleDao
    companion object {
        const val DATABASE_NAME = "school.db"
    }
}