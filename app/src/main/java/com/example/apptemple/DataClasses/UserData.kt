package com.example.apptemple.DataClasses

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("last_name") val lastName: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    val email: String? = null,
    val username: String? = null,
    val password: String? = null,
)