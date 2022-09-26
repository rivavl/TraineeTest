package com.marina.traineetest.presentation.view_model

import androidx.lifecycle.LiveData
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
    val coinsList: LiveData<UiState<List<Coin>>> get() = _coinsList

    private var _refreshFailed = MutableLiveData<Boolean>()
    val refreshFailed: LiveData<Boolean> get() = _refreshFailed

    fun getCoinsList(currency: String, refresh: Boolean) = viewModelScope.launch {
        getCurrencyListUseCase(currency).collect { result ->
            when (result) {
                is Resource.Success -> {
                    setCoins(result, currency, refresh)
                }
                is Resource.Error -> {
                    setError(result, refresh)
                }
                is Resource.Loading -> {
                    setLoading(refresh)
                }
            }
        }
    }

    fun refresh(currency: String) {
        getCoinsList(currency, true)
    }

    private fun setCoins(result: Resource<List<CoinEntity>>, currency: String, refresh: Boolean) {
        val coins = result.data?.toUI(currency) ?: mutableListOf()
        _coinsList.postValue(UiState.Success(coins))
        if (refresh) {
            _refreshFailed.postValue(false)
        }
    }

    private fun setError(result: Resource<List<CoinEntity>>, refresh: Boolean) {
        if (refresh) {
            _refreshFailed.postValue(true)
        } else {
            when (result.error!!) {
                INTERNET_CONNECTION -> {
                    _coinsList.postValue(UiState.Error(ErrorOnUI.INTERNET_CONNECTION))
                }
                UNKNOWN -> {
                    _coinsList.postValue(UiState.Error(ErrorOnUI.UNKNOWN))
                }
            }
        }
    }

    private fun setLoading(refresh: Boolean) {
        if (!refresh) {
            _coinsList.postValue(UiState.Loading())
        }
    }

    companion object {
        val USD = "usd"
        val EUR = "eur"
    }
}