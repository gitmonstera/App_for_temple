package com.example.apptemple.data.database

import androidx.room.*

@Entity//аннотация нужна, чтобы программа понимала, что это таблица базы данных
data class User(
    val username : String,
    val password : String,
    val email : String,
    val name : String,
    val surname : String,
    val birthDate : String,
    val role : String,
    val lastActive : String,//дата последнего входа
    @PrimaryKey(autoGenerate = true)//первичный ключ в таблице, а также пусть программа сама его генерирует, чтобы мы его не поломали
    val id : Int? = null
)
@Entity
data class Event(
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val location: String,
    val organizer: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
@Entity
data class Article(
    val name: String,
    val content: String,
    val description: String,
    val date: String,
    val type: String,//анонс или история
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)