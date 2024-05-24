package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopshop.databinding.ItemProductBinding
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.products.ProductsItem

class ProductAdapter(private val onClick: (ProductsItem) -> Unit) :
    ListAdapter<ProductsItem, ProductAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (ProductsItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductsItem) {
            binding.productImg.load(item.Data.PictureModels[0].ImageUrl)
            binding.productNameTv.text = item.Data.Name
            binding.productRating.rating =
                if (item.Data.ProductReviewOverview.TotalReviews == 0) 0f else (item.Data.ProductReviewOverview.RatingSum / item.Data.ProductReviewOverview.TotalReviews).toFloat()
            binding.productPriceTv.text = "$${item.Data.ProductPrice}"

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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductsItem>() {
            override fun areItemsTheSame(
                oldItem: ProductsItem, newItem: ProductsItem
            ): Boolean {
                return oldItem.Data.Id == newItem.Data.Id
            }

            override fun areContentsTheSame(
                oldItem: ProductsItem, newItem: ProductsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}