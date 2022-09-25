package com.marina.traineetest.data.mapper

import com.marina.traineetest.data.network.dto.CoinInListDto
import com.marina.traineetest.data.network.dto.detail.CoinDto
import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.entity.SingleCoinEntity

fun List<CoinInListDto>.toDomain(): List<CoinEntity> {
    return map {
        it.toCoinEntity()
    }
}

fun CoinInListDto.toCoinEntity(): CoinEntity {
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

fun CoinDto.toSingleCoinEntity(): SingleCoinEntity {
    return SingleCoinEntity(
        name = name,
        image = image.large,
        description = description.data,
        categories = categories
    )
}