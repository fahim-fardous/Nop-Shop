package com.example.nopshop.network.api

import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponse
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.model.featureProducts.FeatureProductItem
import com.example.nopshop.model.slider.ImageSliderItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApi {
    @GET("slider/homepageslider")
    suspend fun getSliderImages(): Response<ImageSliderItem>

    @GET("home/homepagecategorieswithproducts")
    suspend fun getCategoriesWithProducts(): Response<CategoryWiseProductsItem>

    @GET("home/featureproducts")
    suspend fun getFeatureProducts(): Response<FeatureProductItem>

    @POST("shoppingCart/AddProductToCart/details/{productId}/1")
    suspend fun addToCart(
        @Path("productId") productId: Int,
        @Body body: AddToCartItem
    ): Response<AddToCartResponse>
}