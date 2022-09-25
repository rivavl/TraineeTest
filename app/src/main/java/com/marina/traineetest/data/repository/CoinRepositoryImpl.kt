package com.marina.traineetest.data.repository

import com.marina.traineetest.data.mapper.toCoinEntity
import com.marina.traineetest.data.mapper.toDomain
import com.marina.traineetest.data.network.CoinApi
import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override suspend fun getCoin(id: String): CoinEntity? {
        val response = api.getSingleCoin(id)
        return response.body()?.toCoinEntity()
    }

    override suspend fun getCoinsList(currency: String): List<CoinEntity>? {
        val response = api.getCoinsList(currency)
        return response.body()?.toDomain()
    }
}