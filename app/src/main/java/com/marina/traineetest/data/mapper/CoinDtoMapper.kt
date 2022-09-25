package com.marina.traineetest.data.mapper

import com.marina.traineetest.data.network.dto.CoinDto
import com.marina.traineetest.domain.entity.CoinEntity

fun List<CoinDto>.toDomain(): List<CoinEntity> {
    return map {
        it.toCoinEntity()
    }
}

fun CoinDto.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        name = name,
        symbol = symbol,
        imageUrl = imageUrl,
        price = format(currentPrice),
        priceChangePercentage = percentageToString(priceChangePercentage)
    )
}

/**
 * Преобразование процентов в строку
 * */
private fun percentageToString(percentage: Double): String {
    return format(percentage) + "%"
}

/**
 * Округление чисел, по умолчанию 2 знака после запятой
 * */
private fun format(value: Double, digits: Int = 2): String {
    return "%.${digits}f".format(value)
}