package com.marina.traineetest.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.marina.traineetest.R
import com.marina.traineetest.app.App
import com.marina.traineetest.databinding.FragmentCoinDetailBinding
import com.marina.traineetest.presentation.entity.CoinDetail
import com.marina.traineetest.presentation.util.UiState
import com.marina.traineetest.presentation.view_model.CoinDetailViewModel
import com.marina.traineetest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class CoinDetailFragment : Fragment(R.layout.fragment_coin_detail) {

    private val currentId by lazy {
        requireArguments().getString(COIN_ID, "")
    }

    private lateinit var binding: FragmentCoinDetailBinding
    private lateinit var viewModel: CoinDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinDetailViewModel::class.java]
        binding = FragmentCoinDetailBinding.bind(view)
        setupClickListener()
        observeViewModel()
    }

    private fun setupClickListener() {
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.errorLt.btnTryAgain.setOnClickListener {
            viewModel.getCoinInfo(currentId)
        }
    }

    private fun observeViewModel() {
        viewModel.getCoinInfo(currentId)
        viewModel.coin.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    setLoading()
                }
                is UiState.Error -> {
                    setError()
                }
                is UiState.Success -> {
                    setResult(state.data)
                }
            }
        }
    }

    private fun setLoading() = with(binding) {
        progressBar.isVisible = true
        errorLt.layoutError.isVisible = false
        scrollView.isVisible = false
    }

    private fun setResult(coin: CoinDetail?) = with(binding) {
        progressBar.isVisible = false
        errorLt.layoutError.isVisible = false
        scrollView.isVisible = true
        loadImage(coin?.image!!, ivImage, requireContext())
        tvDescription.text = coin.description
        tvCategories.text = coin.categories.toString()
        tvTitle.text = coin.name
    }

    private fun setError() = with(binding) {
        progressBar.isVisible = false
        scrollView.isVisible = false
        errorLt.layoutError.isVisible = true
    }

    private fun loadImage(url: String, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    companion object {
        private const val COIN_ID = "coin_id"

        fun getInstance(id: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(COIN_ID, id)
                }
            }
        }
    }
}