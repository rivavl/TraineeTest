package com.marina.traineetest.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.traineetest.domain.entity.CoinEntity
import com.marina.traineetest.domain.use_case.GetCurrencyListUseCase
import com.marina.traineetest.domain.util.Error.INTERNET_CONNECTION
import com.marina.traineetest.domain.util.Error.UNKNOWN
import com.marina.traineetest.domain.util.Resource
import com.marina.traineetest.presentation.entity.Coin
import com.marina.traineetest.presentation.mapper.toUI
import com.marina.traineetest.presentation.util.ErrorOnUI
import com.marina.traineetest.presentation.util.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : ViewModel() {

    private var _coinsList = MutableLiveData<UiState<List<Coin>>>()
    val coinsList: MutableLiveData<UiState<List<Coin>>> get() = _coinsList

    fun getCoinsList(currency: String) = viewModelScope.launch {
        getCurrencyListUseCase(currency).collect { result ->
            when (result) {
                is Resource.Success -> {
                    setCoins(result, currency)
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

    private fun setCoins(result: Resource<List<CoinEntity>>, currency: String) {
        val coins = result.data?.toUI(currency) ?: mutableListOf()
        _coinsList.postValue(UiState.Success(coins))
    }

    private fun setError(result: Resource<List<CoinEntity>>) {
        when (result.error!!) {
            INTERNET_CONNECTION -> {
                _coinsList.postValue(UiState.Error(ErrorOnUI.INTERNET_CONNECTION))
            }
            UNKNOWN -> {
                _coinsList.postValue(UiState.Error(ErrorOnUI.UNKNOWN))
            }
        }
    }

    private fun setLoading() {
        _coinsList.postValue(UiState.Loading())
    }

    companion object {
        val USD = "usd"
        val EUR = "eur"
    }
}