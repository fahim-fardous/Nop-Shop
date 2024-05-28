package com.example.nopshop.screen.product.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(context: Context) : ViewModel() {
    private val _productAddedToCart: MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }

    val productAddedToCart: LiveData<AddToCartResponse>
        get() = _productAddedToCart


    private val apiClient = ApiClient.getRetrofit(null).create(ProductApi::class.java)
    private val db = AppDatabase.invoke(context)
    private val repository = ProductRepository(db, apiClient)
    fun addProductToCart(productId: Int, quantity: Int) = viewModelScope.launch {
        try {
            val response = repository.addProductToCart(
                AddToCartItem(
                    listOf(
                        FormValue(
                            "addtocart_$productId.EnteredQuantity",
                            "$quantity"
                        )
                    )
                ), productId
            )
            _productAddedToCart.value = response.body()
        } catch (e: Exception) {

        }

    }

}