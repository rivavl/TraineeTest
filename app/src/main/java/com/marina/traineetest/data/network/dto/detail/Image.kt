package com.marina.traineetest.data.network.dto.detail

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("large")
    val large: String
)