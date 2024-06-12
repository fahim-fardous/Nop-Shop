package com.example.nopshop.screen.authentication.account

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val success: LiveData<Boolean>
        get() = _success

    private val _login: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val login: LiveData<Boolean>
        get() = _login

    private val _orderCount:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val orderCount:LiveData<Int>
        get() = _orderCount

    fun logOut() = viewModelScope.launch {
        sharedPreferences.edit().clear().apply()
        Constants.TOKEN = null
        _success.value = true
    }

    fun login() = viewModelScope.launch {
        val token = sharedPreferences.getString("auth_token", "")
        if(token!=null){
            Constants.TOKEN = token
            _login.value = true
        }
    }

    fun getAccountInfo() = viewModelScope.launch {

    }

    fun getOrders() = viewModelScope.launch {
        val response = productRepository.getOrdersFromDb(sharedPreferences.getString("email","")!!)
        _orderCount.value = response.size
    }
}