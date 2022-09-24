package com.marina.traineetest.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: Double,
)