package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nopshop.databinding.ItemCategoryBinding
import com.example.nopshop.databinding.ItemCategoryListBinding
import com.example.nopshop.model.CategoryItem

class CategoryListAdapter(
    private val onCategoryClick: (CategoryItem) -> Unit
) : ListAdapter<CategoryItem, CategoryListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemCategoryListBinding,
        private val onCategoryClick: (CategoryItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryItem) {
            binding.categoryImg.setImageResource(category.categoryImage)
            binding.categoryName.text = category.categoryName
            binding.root.setOnClickListener {
                onCategoryClick(category)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onCategoryClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        return holder.bind(category)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryItem>() {
            override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}