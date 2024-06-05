package com.example.nopshop.screen.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.cart.Item
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _cartResponse: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val cartResponse: LiveData<CartItemResponse>
        get() = _cartResponse

    private val _cartUpdateResponse: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val cartUpdateResponse: LiveData<CartItemResponse>
        get() = _cartUpdateResponse

    private val _cartRemoveResponse: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val cartRemoveResponse: LiveData<CartItemResponse>
        get() = _cartRemoveResponse


    fun getCartProducts() = viewModelScope.launch {
        try {
            val response = repository.getCartItems()
            _cartResponse.value = response.body()
        } catch (e: Exception) {

        }
    }

    fun removeCart(item: Item) = viewModelScope.launch {
        try {
            val response = repository.removeCart(
                AddToCartItem(
                    listOf(
                        FormValue(
                            Key = "removefromcart",
                            Value = "${item.Id}"
                        )
                    )
                )
            )
            _cartRemoveResponse.value = response.body()
        } catch (e: Exception) {
        }
    }

    fun updateCart(item: Item, quantity: Int?) = viewModelScope.launch {
        try {
            val response = repository.updateCart(
                AddToCartItem(
                    listOf(
                        FormValue(
                            Key = "itemquantity${item.Id}",
                            Value = "$quantity"
                        )
                    )
                )
            )
            _cartUpdateResponse.value = response.body()

        } catch (e: Exception) {
        }
    }
}