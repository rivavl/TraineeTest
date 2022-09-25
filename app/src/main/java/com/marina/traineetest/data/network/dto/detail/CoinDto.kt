package com.marina.traineetest.data.network.dto.detail

import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("description")
    val description: Description,
    @SerializedName("categories")
    val categories: List<String>,
)