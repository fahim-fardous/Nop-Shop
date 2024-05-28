package com.example.nopshop.screen.product.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductListViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}