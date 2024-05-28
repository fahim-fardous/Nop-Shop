package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopshop.databinding.ItemProductBinding
import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.category.Product
import com.example.nopshop.model.products.ProductsItem

class ProductAdapter(
    private val onClick: (Product) -> Unit,
    private val onAddToCart: (Product) -> Unit
) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (Product) -> Unit,
        private val onAddToCart: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.productImg.load(item.PictureModels[0].ImageUrl)
            binding.productNameTv.text = item.Name
            binding.productRating.rating =
                if (item.ReviewOverviewModel.TotalReviews == 0) 0f else (item.ReviewOverviewModel.RatingSum / item.ReviewOverviewModel.TotalReviews).toFloat()
            binding.productPriceTv.text = item.ProductPrice.Price
            binding.addToCartBtn.setOnClickListener{
                onAddToCart(item)
            }
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
        return ViewHolder(binding, onClick, onAddToCart)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val product = getItem(position)
        return holder.bind(product)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product, newItem: Product
            ): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: Product, newItem: Product
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}