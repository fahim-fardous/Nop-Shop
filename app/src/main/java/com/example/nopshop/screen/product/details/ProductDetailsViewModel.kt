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
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val productRepository: ProductRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
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

    private val _itemCount: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val itemCount: LiveData<CartItemResponse>
        get() = _itemCount


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
        if (response.isSuccessful) _productAddedToCart.value = response.body()
    }
    fun getCartItemCount() = viewModelScope.launch {
        try {
            val response = productRepository.getCartItems()
            val count = response.body()
            _itemCount.value = count
        } catch (e: Exception) {
        }
    }
}