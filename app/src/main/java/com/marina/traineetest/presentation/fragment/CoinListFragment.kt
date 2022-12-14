package com.marina.traineetest.presentation.fragment

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.marina.traineetest.R
import com.marina.traineetest.app.App
import com.marina.traineetest.databinding.FragmentCoinListBinding
import com.marina.traineetest.presentation.adapter.CoinAdapter
import com.marina.traineetest.presentation.entity.Coin
import com.marina.traineetest.presentation.util.UiState
import com.marina.traineetest.presentation.view_model.CoinListViewModel
import com.marina.traineetest.presentation.view_model.CoinListViewModel.Companion.EUR
import com.marina.traineetest.presentation.view_model.CoinListViewModel.Companion.USD
import com.marina.traineetest.presentation.view_model.ViewModelFactory
import javax.inject.Inject

class CoinListFragment : Fragment(R.layout.fragment_coin_list) {

    private lateinit var binding: FragmentCoinListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinsListAdapter: CoinAdapter
    private lateinit var viewModel: CoinListViewModel
    private var lastQueryCurrency = USD

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
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinListViewModel::class.java]
        binding = FragmentCoinListBinding.bind(view)
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
        viewModel.getCoinsList(lastQueryCurrency, false)
    }

    private fun setupClickListeners() {
        binding.usd.setOnClickListener {
            lastQueryCurrency = USD
            viewModel.getCoinsList(USD, false)
        }
        binding.eur.setOnClickListener {
            lastQueryCurrency = EUR
            viewModel.getCoinsList(EUR, false)
        }
        binding.errorLt.btnTryAgain.setOnClickListener {
            viewModel.getCoinsList(lastQueryCurrency, false)
        }
        binding.srlRefresh.setOnRefreshListener {
            viewModel.refresh(lastQueryCurrency)
        }
    }

    private fun observeViewModel() {
        viewModel.coinsList.observe(viewLifecycleOwner) { state ->
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
        viewModel.refreshFailed.observe(viewLifecycleOwner) {
            if (it) {
                showSnackbar(requireContext().getString(R.string.refresh_error))
                binding.srlRefresh.isRefreshing = false
            }
        }
    }

    private fun showSnackbar(info: String) {
        Snackbar.make(
            binding.root,
            info,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setLoading() = with(binding) {
        progressBar.isVisible = true
        rvCoins.isVisible = false
        errorLt.layoutError.isVisible = false
    }

    private fun setResult(coins: List<Coin>?) = with(binding) {
        progressBar.isVisible = false
        rvCoins.isVisible = true
        errorLt.layoutError.isVisible = false
        srlRefresh.isRefreshing = false
        coinsListAdapter.submitList(coins)
    }

    private fun setError() = with(binding) {
        progressBar.isVisible = false
        rvCoins.isVisible = false
        errorLt.layoutError.isVisible = true
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvCoins
        coinsListAdapter = CoinAdapter()
        recyclerView.apply {
            adapter = coinsListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        coinsListAdapter.onCoinItemClick = {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, CoinDetailFragment.getInstance(it.id))
                .commit()
        }
    }
}