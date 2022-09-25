package com.marina.traineetest.presentation.mapper

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.entity.SingleCoinEntity
import com.marina.traineetest.presentation.entity.Coin
import com.marina.traineetest.presentation.entity.CoinDetail


fun List<CoinEntity>.toUI(currency: String): List<Coin> {
    return map {
        it.toCoin(currency)
    }
}

fun CoinEntity.toCoin(currency: String): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        imageUrl = imageUrl,
        price = "${getCurrencySymbol(currency)} $price",
        priceChangePercentage = priceChangePercentage,
        negativePercentage = isNegative(priceChangePercentage)
    )
}

private fun isNegative(percentage: String): Boolean {
    return percentage.startsWith("-")
}

private fun getCurrencySymbol(currency: String): String {
    return when (currency) {
        "usd" -> "$"
        "eur" -> "€"
        else -> ""
    }
}

fun SingleCoinEntity.toCoinDetail(): CoinDetail {
    return CoinDetail(
        name = name,
        image = image,
        description = description,
        categories = categories
    )
}