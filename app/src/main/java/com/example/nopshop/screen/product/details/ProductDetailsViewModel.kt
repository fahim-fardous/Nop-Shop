package com.example.nopshop.screen.product.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailsViewModel:ViewModel() {
    private val _productResponse: MutableLiveData<ProductsItem> by lazy {
        MutableLiveData<ProductsItem>()
    }

    val productResponse: LiveData<ProductsItem>
        get() = _productResponse

    val apiClient = ApiClient.getRetrofit().create(ProductApi::class.java)
    val repository = ProductRepository(apiClient)

    fun getProducts(id: Int) = viewModelScope.launch {
        try {
            val response = repository.getProductDetails(id)
            _productResponse.value = response.body()
        } catch (e: Exception) {

        }
    }
}