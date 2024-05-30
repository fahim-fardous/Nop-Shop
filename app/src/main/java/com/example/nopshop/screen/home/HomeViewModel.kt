package com.example.nopshop.screen.home

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.db.dbmodel.category.CategoryEntity
import com.example.nopshop.db.dbmodel.featureProduct.FeatureProductsEntity
import com.example.nopshop.db.dbmodel.slider.SliderEntity
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponse
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.featureProducts.FeatureProductItem
import com.example.nopshop.model.slider.Slider
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.HomeApi
import com.example.nopshop.repository.HomeRepository
import com.example.nopshop.utils.NoInternet
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _sliderImageResponse: MutableLiveData<List<Slider>> by lazy {
        MutableLiveData<List<Slider>>()
    }
    val sliderImageResponse: LiveData<List<Slider>>
        get() = _sliderImageResponse

    private val _sliderImageResponseFromDb: MutableLiveData<List<SliderEntity>> by lazy {
        MutableLiveData<List<SliderEntity>>()
    }
    val sliderImageResponseFromDb: LiveData<List<SliderEntity>>
        get() = _sliderImageResponseFromDb

    private val _categoryWiseProductsResponse: MutableLiveData<CategoryWiseProductsItem> by lazy {
        MutableLiveData<CategoryWiseProductsItem>()
    }

    val categoryWiseProductsResponse: LiveData<CategoryWiseProductsItem>
        get() = _categoryWiseProductsResponse

    private val _categoryWiseProductsResponseFromDb: MutableLiveData<List<CategoryEntity>> by lazy {
        MutableLiveData<List<CategoryEntity>>()
    }

    val categoryWiseProductsResponseFromDb: LiveData<List<CategoryEntity>>
        get() = _categoryWiseProductsResponseFromDb

    private val _featureProductsResponseFromDb: MutableLiveData<List<FeatureProductsEntity>> by lazy {
        MutableLiveData<List<FeatureProductsEntity>>()
    }

    val featureProductsResponseFromDb: LiveData<List<FeatureProductsEntity>>
        get() = _featureProductsResponseFromDb
    private val _featureProductsResponse: MutableLiveData<FeatureProductItem> by lazy {
        MutableLiveData<FeatureProductItem>()
    }

    val featureProductsResponse: LiveData<FeatureProductItem>
        get() = _featureProductsResponse
    private val _productAddedToCart: MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }

    val productAddedToCart: LiveData<AddToCartResponse>
        get() = _productAddedToCart


    private val token =
        context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE).getString("token", null)


    fun getImageSlider(context: Context) = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getImageSlider()
            _sliderImageResponse.value = response.body()?.Data?.Sliders
        } else {
            val response = repository.getImageSliderFromDb()
            _sliderImageResponseFromDb.value = response
        }
    }

    fun getCategoryWiseProducts(context: Context) = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getCategoryWiseProducts()
            _categoryWiseProductsResponse.value = response.body()
        } else {
            val response = repository.getCategoryWiseProductsFromDb()
            _categoryWiseProductsResponseFromDb.value = response
        }
    }

    fun getFeatureProducts(context: Context) = viewModelScope.launch {
        if (NoInternet.isOnline(context.applicationContext)) {
            val response = repository.getFeatureProducts()
            _featureProductsResponse.value = response.body()
        } else {
            val response = repository.getFeatureProductsFromDb()
            _featureProductsResponseFromDb.value = response
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