package com.example.nopshop.network.api

import com.example.nopshop.model.ProductItem
import com.example.nopshop.model.products.ProductsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("product/productdetails/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int ) : Response<ProductsItem>
}