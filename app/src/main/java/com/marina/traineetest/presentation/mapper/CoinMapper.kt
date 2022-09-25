package com.marina.traineetest.presentation.mapper

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.presentation.entity.Coin


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