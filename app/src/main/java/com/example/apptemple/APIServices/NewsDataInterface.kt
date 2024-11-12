package com.example.apptemple.APIServices

import com.example.apptemple.DataClasses.NewsData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface NewsDataInterface {
    @GET("news/")
    suspend fun getNews(): List<NewsData>
}