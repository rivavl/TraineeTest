package com.marina.traineetest.domain.use_case

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetSingleCurrencyUseCase {

    suspend operator fun invoke(): Flow<Resource<CoinEntity>> = flow {
        try {
            emit(Resource.Loading())
            val coins = getCoin()
            emit(Resource.Success(coins.data))
        } catch (e: IOException) {
            emit(Resource.Error("Отсутствует интернет соединение\n Попробуйте позже"))
        } catch (e: Exception) {
            emit(Resource.Error("Случилась какая-то ошибка :(\nПопробуем снова?"))
        }
    }
}