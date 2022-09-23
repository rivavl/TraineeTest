package com.marina.traineetest.data.network

import com.marina.traineetest.data.dto.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {

    @GET("")
    suspend fun getCoinsList(@Query("vs_currency") currency: String): Response<List<CoinDto>>

    @GET("")
    suspend fun getSingleCoin(id: String): Response<CoinDto>
}