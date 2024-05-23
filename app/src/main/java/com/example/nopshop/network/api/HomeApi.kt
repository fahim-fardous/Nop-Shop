package com.example.nopshop.network.api

import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.featureProducts.FeatureProductItem
import com.example.nopshop.model.slider.ImageSliderItem
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {
    @GET("slider/homepageslider")
    suspend fun getSliderImages(): Response<ImageSliderItem>

    @GET("home/homepagecategorieswithproducts")
    suspend fun getCategoriesWithProducts(): Response<CategoryWiseProductsItem>

    @GET("home/featureproducts")
    suspend fun getFeatureProducts(): Response<FeatureProductItem>
}