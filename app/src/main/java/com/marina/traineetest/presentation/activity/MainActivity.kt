package com.marina.traineetest.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.traineetest.R
import com.marina.traineetest.data.network.RetrofitInstance
import com.marina.traineetest.presentation.fragment.CoinListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CoinListFragment())
            .commit()
    }
}
