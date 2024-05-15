package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nopshop.databinding.ItemProductBinding
import com.example.nopshop.model.ProductItem

class BestSellingAdapter(private val onClick: (ProductItem) -> Unit) :
    ListAdapter<ProductItem, BestSellingAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (ProductItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductItem) {
            binding.productImg.setImageResource(item.productImage)
            binding.productNameTv.text = item.productName
            binding.productRating.rating = item.rating
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(
                oldItem: ProductItem, newItem: ProductItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductItem, newItem: ProductItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}