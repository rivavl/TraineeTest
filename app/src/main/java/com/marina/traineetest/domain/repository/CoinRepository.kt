package com.marina.traineetest.domain.repository

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.entity.SingleCoinEntity

interface CoinRepository {

    suspend fun getCoin(id: String): SingleCoinEntity?

    suspend fun getCoinsList(currency: String): List<CoinEntity>?
}