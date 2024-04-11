package com.example.apptemple.data.database

import androidx.room.*

@Dao//необходимо для работы с базой данных
interface UserDao {
    //под капотом реализует sql-запрос, который вставляет данные в таблицу. При конфликте первичных ключей запрос заменяет старую запись на новую
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user : User)
    @Delete
    fun delete(user : User)
    @Query("SELECT * FROM user")//выполняется sql-запрос для получения всех данных
    fun getAllData() : List<User>
}
@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event : Event)
    @Delete
    fun delete(event : Event)
    @Query("DELETE FROM event")//удаление всех записей из таблице
    fun deleteAllData()
    @Query("SELECT * FROM event")
    fun getAllData() : List<Event>
}
@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article : Article)
    @Delete
    fun delete(article : Article)
    @Query("DELETE FROM event")
    fun deleteAllData()
    @Query("SELECT * FROM article")
    fun getAllData() : List<Article>
}