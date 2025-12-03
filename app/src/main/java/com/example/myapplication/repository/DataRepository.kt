package com.example.myapplication.repository

import com.example.myapplication.data.api.ApiService
import com.example.myapplication.data.models.PhotoListResponseItem


class DataRepository(private val apiService: ApiService) {

    suspend fun getPhotoList() : retrofit2.Response<List<PhotoListResponseItem>> {
        return apiService.getPhotoList()
    }
}