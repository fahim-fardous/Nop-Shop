package com.example.nopshop.repository

import android.content.Context
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.model.category.asEntity
import com.example.nopshop.model.slider.ImageSliderItem
import com.example.nopshop.model.slider.Slider
import com.example.nopshop.model.slider.asEntity
import com.example.nopshop.network.api.HomeApi
import com.example.nopshop.utils.NoInternet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class HomeRepository(
    private val context: Context,
    private val api: HomeApi,
    private val db: AppDatabase
) {
    suspend fun getImageSlider() = withContext(Dispatchers.IO) {
        val sliders = api.getSliderImages()
        sliders.let {
            sliders.body()?.Data?.Sliders?.let { it1 ->
                db.sliderDao().saveImageSlider(it1.map { it.asEntity() })
            }
        }
        return@withContext sliders
    }

    suspend fun getCategoryWiseProducts() = withContext(Dispatchers.IO) {
        val categories = api.getCategoriesWithProducts()
        categories.let {
            categories.body()?.Data?.let {list->
                list.map { it.asEntity() }.let { it2 -> db.categoryDao().saveCategory(it2) }
            }
        }
        categories
    }

    suspend fun getFeatureProducts() = withContext(Dispatchers.IO) {
        return@withContext api.getFeatureProducts()
    }
    // Database
    suspend fun getImageSliderFromDb() = withContext(Dispatchers.IO) {
        return@withContext db.sliderDao().getImageSlider()
    }

    suspend fun getCategoryWiseProductsFromDb() = withContext(Dispatchers.IO) {
        return@withContext db.categoryDao().getAllCategory()

    }

}