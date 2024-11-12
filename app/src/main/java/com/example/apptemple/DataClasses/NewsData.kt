package com.example.apptemple.DataClasses

import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("title") val newsTitle: String,
    @SerializedName("content") val newsDescription: String,
    @SerializedName("photo_url") val newsImage: String
)