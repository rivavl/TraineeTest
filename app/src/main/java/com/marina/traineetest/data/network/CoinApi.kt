package com.marina.traineetest.data.network

import com.marina.traineetest.data.network.dto.CoinInListDto
import com.marina.traineetest.data.network.dto.detail.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {

    @GET("coins/markets")
    suspend fun getCoinsList(
        @Query("vs_currency") currency: String,
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1
    ): Response<List<CoinInListDto>>

    @GET("coins/{id}")
    suspend fun getSingleCoin(@Path("id") id: String): Response<CoinDto>
}