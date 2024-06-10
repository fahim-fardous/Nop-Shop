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
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _productAddedToCart: MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }

    val productAddedToCart: LiveData<AddToCartResponse>
        get() = _productAddedToCart

    private val _itemCount: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val itemCount: LiveData<Int>
        get() = _itemCount

    fun addProductToCart(productId: Int, quantity: Int) = viewModelScope.launch {
        try {
            val response = productRepository.addProductToCart(
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
    fun getCartItemCount() = viewModelScope.launch {
        try {
            val response = productRepository.getCartItems()
            val count = response.body()
            if (count != null) {
                _itemCount.value = count.Data.Cart.Items.size
            }
        } catch (e: Exception) {
        }
    }

}