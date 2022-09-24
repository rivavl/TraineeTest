package com.marina.traineetest.di.module

import androidx.lifecycle.ViewModel
import com.marina.traineetest.di.annotations.ViewModelKey
import com.marina.traineetest.presentation.view_model.CoinDetailViewModel
import com.marina.traineetest.presentation.view_model.CoinListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    fun bindDetailFragmentViewModel(viewModel: CoinDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    fun bindListFragmentViewModel(viewModel: CoinListViewModel): ViewModel
}