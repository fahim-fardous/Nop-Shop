package com.example.nopshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopshop.databinding.ItemCategoryBinding
import com.example.nopshop.model.category.Data

class CategoryListAdapter(
    private val onCategoryClick: (Data, String) -> Unit
) : ListAdapter<Data, CategoryListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val onCategoryClick: (Data, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Data) {
            binding.categoryName.text = category.Name
            binding.categoryImg.load(category.Products[0].PictureModels[0].ImageUrl)
            binding.root.setOnClickListener {
                onCategoryClick(category, category.Name)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onCategoryClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        return holder.bind(category)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }
    }
}