package com.example.nopshop.screen.home

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.db.dbmodel.category.CategoryEntity
import com.example.nopshop.db.dbmodel.category.toData
import com.example.nopshop.db.dbmodel.featureProduct.FeatureProductsEntity
import com.example.nopshop.db.dbmodel.featureProduct.toFeatureData
import com.example.nopshop.db.dbmodel.slider.SliderEntity
import com.example.nopshop.db.dbmodel.slider.toSlider
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponse
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.featureProducts.Data
import com.example.nopshop.model.featureProducts.FeatureProductItem
import com.example.nopshop.model.slider.Slider
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.HomeApi
import com.example.nopshop.repository.HomeRepository
import com.example.nopshop.repository.ProductRepository
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val productRepository: ProductRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _sliderImageResponse: MutableLiveData<List<Slider>> by lazy {
        MutableLiveData<List<Slider>>()
    }
    val sliderImageResponse: LiveData<List<Slider>>
        get() = _sliderImageResponse


    private val _categoryWiseProductsResponse: MutableLiveData<List<com.example.nopshop.model.category.Data>> by lazy {
        MutableLiveData<List<com.example.nopshop.model.category.Data>>()
    }

    val categoryWiseProductsResponse: LiveData<List<com.example.nopshop.model.category.Data>>
        get() = _categoryWiseProductsResponse


    private val _featureProductsResponse: MutableLiveData<List<Data>> by lazy {
        MutableLiveData<List<Data>>()
    }

    val featureProductsResponse: LiveData<List<Data>>
        get() = _featureProductsResponse

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

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val showMessage: LiveData<String>
        get() = _showMessage


    fun getImageSlider() = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getImageSlider()
            _sliderImageResponse.value = response.body()?.Data?.Sliders
        } else {
            val response = repository.getImageSliderFromDb()
            _sliderImageResponse.value = response.map {
                it.toSlider()
            }
        }
    }

    fun getCategoryWiseProducts() = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getCategoryWiseProducts()
            _categoryWiseProductsResponse.value = response.body()?.Data
        } else {
            val response = repository.getCategoryWiseProductsFromDb()
            _categoryWiseProductsResponse.value = response.map {
                it.toData()
            }
        }
    }

    fun getFeatureProducts() = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getFeatureProducts()
            _featureProductsResponse.value = response.body()?.Data
        } else {
            val response = repository.getFeatureProductsFromDb()
            _featureProductsResponse.value = response.map {
                it.toFeatureData()
            }
        }
    }

    fun addProductToCart(productId: Int, quantity: Int) = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.addProductToCart(
                AddToCartItem(
                    listOf(
                        FormValue(
                            "addtocart_$productId.EnteredQuantity", "$quantity"
                        )
                    )
                ), productId
            )
            if (response.isSuccessful) _productAddedToCart.value = response.body()
            else{
                _showMessage.value = "Unable to add to cart"
            }
        } else {
            _showMessage.value = "No Internet Connection"
        }
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