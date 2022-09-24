package com.marina.traineetest.data.repository

import com.marina.traineetest.data.mapper.toCoinEntity
import com.marina.traineetest.data.mapper.toDomain
import com.marina.traineetest.data.network.CoinApi
import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.repository.CoinRepository
import com.marina.traineetest.domain.util.Resource
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override suspend fun getCoin(id: String): Resource<CoinEntity> {
        val response = api.getSingleCoin(id)

        return if (response.isSuccessful) {
            Resource.Success(response.body()?.toCoinEntity()!!)
        } else {
            Resource.Error(response.message())
        }
    }

    override suspend fun getCoinsList(currency: String): Resource<List<CoinEntity>> {
        val response = api.getCoinsList(currency)

        return if (response.isSuccessful) {
            Resource.Success(response.body()?.toDomain()!!)
        } else {
            Resource.Error(response.message())
        }
    }
}