package com.example.myapplication.data.api

import com.example.myapplication.data.models.PhotoListResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getPhotoList(): Response<List<PhotoListResponseItem>>
}
