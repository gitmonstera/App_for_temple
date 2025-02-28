package com.example.apptemple.APIServices

import com.example.apptemple.DataClasses.LoginData
import com.example.apptemple.DataClasses.UserData
import com.example.apptemple.Responses.ServerResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserDataInterface {
    @POST("register")
    fun createUser(@Body userData: UserData): Call<ServerResponse>

    @POST("login")
    fun authorizeUser(@Body loginData: LoginData): Call<ServerResponse>
}