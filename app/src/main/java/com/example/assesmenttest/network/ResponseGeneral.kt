package com.example.assesmenttest.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGeneral<T>(


    @Json(name = "data")
    var data: T?,
//    @Json(name = "exception")
//    val exception: String? = null,

    @Json(name = "ExtendedProperties")
    val ExtendedProperties: List<String>?
)
{
}