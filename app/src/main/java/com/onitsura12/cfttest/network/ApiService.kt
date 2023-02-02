package com.onitsura12.cfttest.network

import com.onitsura12.cfttest.network.models.CardDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{cardNumber}")
    suspend fun getDetailsByNumber(@Path(value = "cardNumber") cardNumber: Long): CardDetails
}