package com.example.nopshop.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.slider.Data
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.ImageSliderApi
import com.example.nopshop.repository.ImageSliderRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _sliderImageResponse: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val sliderImageResponse: LiveData<Data>
        get() = _sliderImageResponse

    private val apiClient = ApiClient.getRetrofit().create(ImageSliderApi::class.java)
    private val repository = ImageSliderRepository(apiClient)

    fun getImageSlider() = viewModelScope.launch {
        val response = repository.getImageSlider()
        _sliderImageResponse.value = response.body()?.Data
    }

}