package com.marina.traineetest.presentation.entity

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val price: String,
    val priceChangePercentage: String,
    val negativePercentage: Boolean
)