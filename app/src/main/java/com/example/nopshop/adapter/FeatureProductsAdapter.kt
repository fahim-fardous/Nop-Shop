package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopshop.databinding.ItemProductBinding
import com.example.nopshop.model.featureProducts.Data

class FeatureProductsAdapter(private val onClick: (Data) -> Unit) :
    ListAdapter<Data, FeatureProductsAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (Data) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.productImg.load(item.PictureModels[0].ImageUrl)
            binding.productNameTv.text = item.Name
            binding.productRating.rating =
                if (item.ReviewOverviewModel.TotalReviews == 0) 0f else (item.ReviewOverviewModel.RatingSum / item.ReviewOverviewModel.TotalReviews).toFloat()
            binding.productPriceTv.text = "${item.ProductPrice.Price}"
            binding.root.setOnClickListener {
                onClick(item)
            }
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(
                oldItem: Data, newItem: Data
            ): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: Data, newItem: Data
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}