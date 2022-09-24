package com.marina.traineetest.app

import android.app.Application
import com.marina.traineetest.di.component.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}