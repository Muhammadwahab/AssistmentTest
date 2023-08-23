package com.example.assesmenttest.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class APIInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }

}