package com.example.nopshop.repository

import com.example.nopshop.network.api.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val api: HomeApi) {
    suspend fun getImageSlider() = withContext(Dispatchers.IO) {
        return@withContext api.getSliderImages()
    }

    suspend fun getCategoryWiseProducts() = withContext(Dispatchers.IO) {
        return@withContext api.getCategoriesWithProducts()
    }

    suspend fun getFeatureProducts() = withContext(Dispatchers.IO) {
        return@withContext api.getFeatureProducts()
    }

}