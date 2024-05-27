package com.example.nopshop.network.api

import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.AddToCartResponseItem
import com.example.nopshop.model.products.ProductsItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {

    @GET("product/productdetails/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int ) : Response<ProductsItem>

    @POST("shoppingCart/AddProductToCart/details/{productId}/1")
    suspend fun addToCart(@Path("productId") productId: Int,@Body body:AddToCartItem): Response<AddToCartResponseItem>
}