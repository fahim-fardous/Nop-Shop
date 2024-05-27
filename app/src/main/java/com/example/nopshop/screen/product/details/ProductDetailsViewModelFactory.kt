package com.example.nopshop.screen.product.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductDetailsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}