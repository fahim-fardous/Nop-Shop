package com.example.nopshop.repository

import com.example.nopshop.network.api.ImageSliderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageSliderRepository(private val api: ImageSliderApi) {
    suspend fun getImageSlider() = withContext(Dispatchers.IO) {
        return@withContext api.getSliderImages()
    }

}