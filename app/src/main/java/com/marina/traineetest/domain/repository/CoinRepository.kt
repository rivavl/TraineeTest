package com.marina.traineetest.domain.repository

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.util.Resource

interface CoinRepository {

    suspend fun getCoin(id: String): Resource<CoinEntity>

    suspend fun getCoinsList(currency: String): Resource<List<CoinEntity>>
}