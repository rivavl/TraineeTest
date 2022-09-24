package com.marina.traineetest.presentation.view_model

import androidx.lifecycle.ViewModel
import com.marina.traineetest.domain.use_case.GetCurrencyListUseCase
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : ViewModel() {
}