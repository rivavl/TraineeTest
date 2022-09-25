package com.marina.traineetest.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.traineetest.R
import com.marina.traineetest.data.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitInstance.coinApi.getSingleCoin("bitcoin")
        }
    }
}
