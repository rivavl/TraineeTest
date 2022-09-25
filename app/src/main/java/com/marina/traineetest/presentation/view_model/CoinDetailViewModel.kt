package com.marina.traineetest.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.traineetest.domain.entity.SingleCoinEntity
import com.marina.traineetest.domain.use_case.GetSingleCoinUseCase
import com.marina.traineetest.domain.util.Error
import com.marina.traineetest.domain.util.Resource
import com.marina.traineetest.presentation.entity.CoinDetail
import com.marina.traineetest.presentation.mapper.toCoinDetail
import com.marina.traineetest.presentation.util.ErrorOnUI
import com.marina.traineetest.presentation.util.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getSingleCurrencyUseCase: GetSingleCoinUseCase
) : ViewModel() {

    private var _coin = MutableLiveData<UiState<CoinDetail>>()
    val coin: MutableLiveData<UiState<CoinDetail>> get() = _coin

    fun getCoinInfo(coinId: String) = viewModelScope.launch {
        getSingleCurrencyUseCase(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    setCoin(result)
                }
                is Resource.Error -> {
                    setError(result)
                }
                is Resource.Loading -> {
                    setLoading()
                }
            }
        }
    }

    private fun setCoin(result: Resource<SingleCoinEntity>) {
        val coin = result.data?.toCoinDetail()
        _coin.postValue(UiState.Success(coin!!))
    }

    private fun setError(result: Resource<SingleCoinEntity>) {
        when (result.error!!) {
            Error.INTERNET_CONNECTION -> {
                _coin.postValue(UiState.Error(ErrorOnUI.INTERNET_CONNECTION))
            }
            Error.UNKNOWN -> {
                _coin.postValue(UiState.Error(ErrorOnUI.UNKNOWN))
            }
        }
    }

    private fun setLoading() {
        _coin.postValue(UiState.Loading())
    }
}