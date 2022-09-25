package com.marina.traineetest.domain.use_case

import com.marina.traineetest.domain.entity.SingleCoinEntity
import com.marina.traineetest.domain.repository.CoinRepository
import com.marina.traineetest.domain.util.Error
import com.marina.traineetest.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSingleCurrencyUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(currency: String): Flow<Resource<SingleCoinEntity>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoin(currency)

            if (coin == null) {
                emit(Resource.Error(Error.UNKNOWN))
            } else {
                emit(Resource.Success(coin))
            }
        } catch (e: IOException) {
            emit(Resource.Error(Error.INTERNET_CONNECTION))
        } catch (e: Exception) {
            emit(Resource.Error(Error.UNKNOWN))
        }
    }
}