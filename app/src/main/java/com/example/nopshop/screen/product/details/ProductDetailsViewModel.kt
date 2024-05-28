package com.example.nopshop.screen.product.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.db.dbmodel.product.ProductEntity
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.NoInternet
import kotlinx.coroutines.launch

class ProductDetailsViewModel(context: Context) : ViewModel() {
    private val _productResponse: MutableLiveData<ProductsItem> by lazy {
        MutableLiveData<ProductsItem>()
    }

    val productResponse: LiveData<ProductsItem>
        get() = _productResponse

    private val _productResponseFromDb: MutableLiveData<ProductEntity> by lazy {
        MutableLiveData<ProductEntity>()
    }

    val productResponseFromDb: LiveData<ProductEntity>
        get() = _productResponseFromDb

    private val _productAddedToCart: MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }

    val productAddedToCart: LiveData<AddToCartResponse>
        get() = _productAddedToCart


    private val apiClient = ApiClient.getRetrofit(null).create(ProductApi::class.java)
    private val db = AppDatabase.invoke(context)
    private val repository = ProductRepository(db, apiClient)

    fun getProducts(context: Context, id: Int) = viewModelScope.launch {
        try {
            if (NoInternet.isOnline(context.applicationContext)) {
                val response = repository.getProductDetails(id)
                _productResponse.value = response.body()
            } else {
                _productResponseFromDb.value = repository.getProductDetailsFromDb(id)
            }

        } catch (e: Exception) {

        }
    }

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