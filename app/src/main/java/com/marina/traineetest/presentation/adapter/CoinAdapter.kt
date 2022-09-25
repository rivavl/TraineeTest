package com.marina.traineetest.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marina.traineetest.R
import com.marina.traineetest.databinding.CoinItemBinding
import com.marina.traineetest.presentation.entity.Coin

class CoinAdapter :
    ListAdapter<Coin, CoinAdapter.CoinItemViewHolder>(CoinDiffUtilCallback()) {

    var onCoinItemClick: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = CoinItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        val coin = getItem(position)
        val context = holder.binding.root.context
        with(holder) {
            loadImage(coin.imageUrl, binding.ivCoin, context)
            binding.tvName.text = coin.name
            binding.tvSubtitle.text = coin.symbol.uppercase()
            binding.tvPrice.text = coin.price
            binding.tvPercent.setTextColor(getPercentageColor(coin.negativePercentage, context))
            binding.tvPercent.text = coin.priceChangePercentage
        }
    }

    private fun getPercentageColor(isNegative: Boolean, context: Context): Int {
        return if (isNegative) {
            context.getColor(R.color.red)
        } else {
            context.getColor(R.color.green)
        }
    }

    private fun loadImage(url: String, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    class CoinItemViewHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    class CoinDiffUtilCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }
}