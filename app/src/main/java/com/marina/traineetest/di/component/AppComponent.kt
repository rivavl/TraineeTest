package com.marina.traineetest.di.component

import android.app.Application
import com.marina.traineetest.di.annotations.ApplicationScope
import com.marina.traineetest.di.module.DataModule
import com.marina.traineetest.di.module.ViewModelModule
import com.marina.traineetest.presentation.fragment.CoinDetailFragment
import com.marina.traineetest.presentation.fragment.CoinListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: CoinDetailFragment)
    fun inject(fragment: CoinListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}