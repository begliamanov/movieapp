package com.example.movieapp.data.network

import okhttp3.ResponseBody

sealed class Result<out R> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val message: String?
    ) : Result<Nothing>()
}
