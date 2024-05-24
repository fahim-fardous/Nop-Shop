package com.example.nopshop.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.featureProducts.FeatureProductItem
import com.example.nopshop.model.slider.Data
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.HomeApi
import com.example.nopshop.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _sliderImageResponse: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val sliderImageResponse: LiveData<Data>
        get() = _sliderImageResponse

    private val _categoryWiseProductsResponse: MutableLiveData<CategoryWiseProductsItem> by lazy {
        MutableLiveData<CategoryWiseProductsItem>()
    }

    val categoryWiseProductsResponse: LiveData<CategoryWiseProductsItem>
        get() = _categoryWiseProductsResponse

    private val _featureProductsResponse: MutableLiveData<FeatureProductItem> by lazy {
        MutableLiveData<FeatureProductItem>()
    }

    val featureProductsResponse: LiveData<FeatureProductItem>
        get() = _featureProductsResponse

    private val apiClient = ApiClient.getRetrofit().create(HomeApi::class.java)
    private val repository = HomeRepository(apiClient)

    fun getImageSlider() = viewModelScope.launch {
        val response = repository.getImageSlider()
        println(response.code())
        _sliderImageResponse.value = response.body()?.Data
    }

    fun getCategoryWiseProducts() = viewModelScope.launch {
        val response = repository.getCategoryWiseProducts()
        _categoryWiseProductsResponse.value = response.body()
    }

    fun getFeatureProducts() = viewModelScope.launch {
        val response = repository.getFeatureProducts()
        _featureProductsResponse.value = response.body()
    }


}