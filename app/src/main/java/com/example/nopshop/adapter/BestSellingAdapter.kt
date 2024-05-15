package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nopshop.R
import com.example.nopshop.databinding.ItemProductBinding
import com.example.nopshop.model.BestSellingItem

class BestSellingAdapter(private val onClick: (BestSellingItem) -> Unit) :
    ListAdapter<BestSellingItem, BestSellingAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (BestSellingItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BestSellingItem) {
            binding.productImg.setImageResource(item.productImage)
            binding.productNameTv.text = item.productName
            binding.productRating.rating = item.rating.toFloat()
            binding.productPriceTv.text = "$${item.price}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClick)

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        return holder.bind(product)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BestSellingItem>() {
            override fun areItemsTheSame(
                oldItem: BestSellingItem, newItem: BestSellingItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BestSellingItem, newItem: BestSellingItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}