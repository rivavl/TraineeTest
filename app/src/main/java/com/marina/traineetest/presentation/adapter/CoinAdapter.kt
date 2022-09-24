package com.marina.traineetest.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marina.traineetest.presentation.entity.Coin

class CoinAdapter :
    ListAdapter<Coin, CoinAdapter.CoinItemViewHolder>(CoinDiffUtilCallback()) {

    var onCoinItemClick: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class CoinItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class CoinDiffUtilCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            TODO("Not yet implemented")
        }

    }
}