package com.marina.traineetest.domain.repository

import com.marina.traineetest.domain.entity.CoinEntity

interface CoinRepository {

    suspend fun getCoin(id: String): CoinEntity?

    suspend fun getCoinsList(currency: String): List<CoinEntity>?
}