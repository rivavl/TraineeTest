package com.marina.traineetest.di.module

import com.marina.traineetest.data.network.CoinApi
import com.marina.traineetest.data.network.RetrofitInstance
import com.marina.traineetest.data.repository.CoinRepositoryImpl
import com.marina.traineetest.di.annotations.ApplicationScope
import com.marina.traineetest.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        fun provideCoinApi(): CoinApi {
            return RetrofitInstance.coinApi
        }
    }

}