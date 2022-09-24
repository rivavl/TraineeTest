package com.marina.traineetest.presentation.mapper

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.presentation.entity.Coin


fun List<CoinEntity>.toDomain(): List<Coin> {
    return map {
        it.toCoin()
    }
}

fun CoinEntity.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        priceChangePercentage = priceChangePercentage
    )
}