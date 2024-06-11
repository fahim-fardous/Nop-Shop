package com.example.nopshop.screen.more

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.dbmodel.order.OrderEntity
import com.example.nopshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val _showLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val showLoading: MutableLiveData<Boolean>
        get() = _showLoading

    private val _orders:MutableLiveData<List<OrderEntity>> by lazy {
        MutableLiveData<List<OrderEntity>>()
    }

    val orders: LiveData<List<OrderEntity>>
        get() = _orders

    fun getOrders() = viewModelScope.launch {
        val email = sharedPreferences.getString("email", "")
        val orders = email?.let { productRepository.getOrdersFromDb(it) }
        if(!orders.isNullOrEmpty()){
            _orders.value = orders
        }
    }
}