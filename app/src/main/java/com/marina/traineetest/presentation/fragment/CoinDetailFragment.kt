package com.marina.traineetest.presentation.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.marina.traineetest.R
import com.marina.traineetest.app.App
import com.marina.traineetest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class CoinDetailFragment : Fragment(R.layout.fragment_coin_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }
}