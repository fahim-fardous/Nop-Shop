package com.example.nopshop.screen.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val showMessage: LiveData<String>
        get() = _showMessage

    private val _removeSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val removeSuccess: LiveData<Boolean>
        get() = _removeSuccess

    private val _cart: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val cart: LiveData<CartItemResponse>
        get() = _cart

    private val _showLoading:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val showLoading:LiveData<Boolean>
    get() = _showLoading

    fun getTotalOrder() = viewModelScope.launch {
        val response = productRepository.getCartItems()
        if (response.isSuccessful) {
            _cart.value = response.body()
        }
    }

    fun removeAllCartItem() = viewModelScope.launch {
        val list = productRepository.getCartItems()
        println(list.body())
        if (list.isSuccessful) {
            val cartList = list.body()?.Data?.Cart?.Items
            if (cartList != null) {
                for (item in cartList) {
                    productRepository.removeCart(
                        AddToCartItem(
                            listOf(
                                FormValue(
                                    Key = "removefromcart", Value = "${item.Id}"
                                )
                            )
                        )
                    )
                }
                _removeSuccess.value = true
            }
        } else {
            println("Something went wrong")
        }
    }

    private fun checkOut() = viewModelScope.launch {
        val retrofit = Retrofit.Builder().baseUrl(Constants.CHECK_OUT_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ProductApi::class.java)
        val response = retrofit.getCheckOutResponse()
        if (response.isSuccessful) {
            _showMessage.value = response.body()?.message.toString()
        } else {
            _showMessage.value = "Something went wrong"
        }
    }

    fun isValid(
        oldAddress: String,
        newAddress: String,
        firstName: String,
        lastName: String,
        email: String,
        company: String,
        country: String,
        state: String,
        zip: String,
        city: String,
        phone: String,
        fax: String
    ) = viewModelScope.launch {
        if (
            oldAddress.isEmpty() ||
            newAddress.isEmpty() ||
            firstName.isEmpty() ||
            lastName.isEmpty() ||
            email.isEmpty() ||
            company.isEmpty() ||
            country.isEmpty() ||
            state.isEmpty() ||
            zip.isEmpty() ||
            city.isEmpty() ||
            phone.isEmpty() ||
            fax.isEmpty()
        ) {
            _showMessage.value = "Please fill all the fields"
        } else {
            checkOut()
        }
    }
}