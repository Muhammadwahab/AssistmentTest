package com.example.assesmenttest.network
sealed class Result<T>(val data: T?) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: String, data: T? = null) : Result<T>(data)
    class Loading<T>(data: T?) : Result<T>(data)
}