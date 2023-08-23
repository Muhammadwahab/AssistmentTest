package com.example.assesmenttest.network

import com.example.assesmenttest.Constants.pixaBayEndPoint
import com.example.assesmenttest.network.response.PixaBayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WebService {



    @GET(pixaBayEndPoint)
    suspend fun getPixaBay(@QueryMap queryParameters: Map<String, String>): Response<PixaBayResponse>

}