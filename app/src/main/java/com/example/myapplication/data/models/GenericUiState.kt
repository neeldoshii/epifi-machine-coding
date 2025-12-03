package com.example.myapplication.data.models

sealed class GenericUiState<out T>{
    object Loading : GenericUiState<Nothing>()
    data class Success<T>(val data : T) : GenericUiState<T>()
    data class Failure(val message: String) : GenericUiState<Nothing>()
}
