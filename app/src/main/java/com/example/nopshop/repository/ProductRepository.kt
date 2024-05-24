package com.example.nopshop.repository

import com.example.nopshop.model.products.ProductsItem
import com.example.nopshop.network.api.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository(private val api: ProductApi) {

    suspend fun getProductDetails(id: Int): Response<ProductsItem> = withContext(Dispatchers.IO) {
        return@withContext api.getProductDetails(id)
    }
}