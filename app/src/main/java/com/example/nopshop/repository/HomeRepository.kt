package com.example.nopshop.repository

import com.example.nopshop.db.AppDatabase
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.category.asEntity
import com.example.nopshop.model.featureProducts.asEntity
import com.example.nopshop.model.slider.asEntity
import com.example.nopshop.network.api.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
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
        val featureProducts = api.getFeatureProducts()

        featureProducts.let {
            featureProducts.body()?.Data.let { it1->
                if (it1 != null) {
                    db.featureProductDao().saveFeatureProducts(it1.map{it.asEntity()})
                }
            }
        }
        featureProducts
    }
    suspend fun addProductToCart(request:AddToCartItem, productId:Int) = withContext(Dispatchers.IO) {

        return@withContext api.addToCart(productId,request)
    }

    // Database
    suspend fun getImageSliderFromDb() = withContext(Dispatchers.IO) {
        return@withContext db.sliderDao().getImageSlider()
    }

    suspend fun getCategoryWiseProductsFromDb() = withContext(Dispatchers.IO) {
        return@withContext db.categoryDao().getAllCategory()
    }

    suspend fun getFeatureProductsFromDb() = withContext(Dispatchers.IO) {
        return@withContext db.featureProductDao().getFeatureProducts()
    }

}