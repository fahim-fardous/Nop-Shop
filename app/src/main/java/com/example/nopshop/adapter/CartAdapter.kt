package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopshop.databinding.ItemCartBinding
import com.example.nopshop.model.CartItem
import com.example.nopshop.model.cart.Item

class CartAdapter(
    private val onRemoveClick: (Item) -> Unit,
    private val onQuantityBtnClick: (Item, Int) -> Unit
) : ListAdapter<Item, CartAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemCartBinding,
        private val onRemoveClick: (Item) -> Unit,
        private val onQuantityBtnClick: (Item, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.productImg.load(item.Picture.ImageUrl)
            binding.productNameTv.text = item.ProductName
            binding.quantityTv.text = "${item.Quantity}"
            binding.originalPrice.text = "$${item.UnitPriceValue}"
            binding.quantityAddBtn.setOnClickListener {
                binding.quantityTv.text =
                    (binding.quantityTv.text.toString().toInt() + 1).toString()
                onQuantityBtnClick(item, binding.quantityTv.text.toString().toInt())
            }
            binding.quantityRemoveBtn.setOnClickListener {
                binding.quantityTv.text =
                    (binding.quantityTv.text.toString().toInt() - 1).toString()
                onQuantityBtnClick(item, binding.quantityTv.text.toString().toInt())
            }
            binding.closeBtn.setOnClickListener {
                onRemoveClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onRemoveClick, onQuantityBtnClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}