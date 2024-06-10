package com.example.nopshop.screen.checkout

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.dbmodel.order.OrderEntity
import com.example.nopshop.model.CheckoutResponse
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.cart.Item
import com.example.nopshop.network.api.ProductApi
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.Constants
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val showMessage: LiveData<String>
        get() = _showMessage
    private val _removeSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val _order: MutableLiveData<CheckoutResponse> by lazy {
        MutableLiveData<CheckoutResponse>()
    }

    val order: LiveData<CheckoutResponse>
        get() = _order

    val removeSuccess: LiveData<Boolean>
        get() = _removeSuccess

    private val _cart: MutableLiveData<CartItemResponse> by lazy {
        MutableLiveData<CartItemResponse>()
    }

    val cart: LiveData<CartItemResponse>
        get() = _cart

    private val _showLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val showLoading: LiveData<Boolean>
        get() = _showLoading

    private val _checkout: MutableLiveData<CheckoutResponse> by lazy {
        MutableLiveData<CheckoutResponse>()
    }
    val checkout: LiveData<CheckoutResponse>
        get() = _checkout

    fun getTotalOrder() = viewModelScope.launch {
        val response = productRepository.getCartItems()
        if (response.isSuccessful) {
            _cart.value = response.body()
        }
    }

    private fun removeAllCartItem() = viewModelScope.launch {
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

    private fun checkOut(orderTotal: String, products: List<Item>) = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val retrofit = Retrofit.Builder().baseUrl(Constants.CHECK_OUT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ProductApi::class.java)
            _showLoading.value = true
            val response = retrofit.getCheckOutResponse()
            if (response.isSuccessful) {
                saveToDb(
                    Constants.TOKEN!!,
                    orderTotal,
                    sharedPreferences.getString("email", ""),
                    response.body()?.orderId.toString(),
                    products
                )
                removeAllCartItem()
                _order.value = response.body()
                _showMessage.value = response.body()?.message.toString()
                _showLoading.value = false
            } else {
                _showMessage.value = "Something went wrong"
            }
        }
        else{
            _showMessage.value = "No Internet Connection"
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
        fax: String,
        orderTotal: String,
        products: List<Item>
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
            checkOut(orderTotal, products)
        }
    }

    private fun saveToDb(
        token: String,
        totalAmount: String,
        email: String?,
        orderId: String,
        products: List<Item>
    ) = viewModelScope.launch {
        println("Aya porchi")
        productRepository.saveOrderToDb(
            OrderEntity(
                token = token,
                email = email!!,
                totalAmount = totalAmount,
                orderId = orderId,
                products = products
            )
        )
    }
}