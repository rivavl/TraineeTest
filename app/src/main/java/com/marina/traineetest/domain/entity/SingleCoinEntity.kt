package com.marina.traineetest.domain.entity

data class SingleCoinEntity(
    val name: String,
    val image: String,
    val description: String,
    val categories: List<String>,
)