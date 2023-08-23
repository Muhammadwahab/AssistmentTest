package com.example.assesmenttest.network.response


import com.squareup.moshi.Json

data class PixaBayResponse(
    @Json(name = "hits")
    val hits: List<Hit?>?,
    @Json(name = "total")
    val total: Int?,
    @Json(name = "totalHits")
    val totalHits: Int?
)