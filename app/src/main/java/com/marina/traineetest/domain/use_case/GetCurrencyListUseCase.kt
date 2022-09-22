package com.marina.traineetest.domain.use_case

import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.repository.CoinRepository
import com.marina.traineetest.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCurrencyListUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(currency: String): Flow<Resource<List<CoinEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoinsList(currency)
            emit(Resource.Success(coins.data!!))
        } catch (e: IOException) {
            emit(Resource.Error("Отсутствует интернет соединение\n Попробуйте позже"))
        } catch (e: Exception) {
            emit(Resource.Error("Случилась какая-то ошибка :(\nПопробуем снова?"))
        }
    }
}