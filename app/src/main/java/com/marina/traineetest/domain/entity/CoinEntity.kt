package com.marina.traineetest.domain.entity

data class CoinEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val price: String,
    val priceChangePercentage: String
)