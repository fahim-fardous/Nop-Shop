package com.example.nopshop.repository

import com.example.nopshop.db.AppDatabase
import com.example.nopshop.db.dbmodel.product.ProductEntity
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.CartItemResponse
import com.example.nopshop.model.products.Data
import com.example.nopshop.network.api.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val db: AppDatabase,
    private val api: ProductApi
) {

    suspend fun getProductDetails(id: Int) = withContext(Dispatchers.IO) {
        val product = api.getProductDetails(id)
        if (product.isSuccessful) {
            db.productDao().saveProduct(product.body()?.Data?.let { it1 -> dataToProductEntity(it1) }!!)
        }
        return@withContext product
    }

    suspend fun addProductToCart(request:AddToCartItem, productId:Int) = withContext(Dispatchers.IO) {

        return@withContext api.addToCart(productId,request)
    }

    suspend fun getCartItems():Response<CartItemResponse> = withContext(Dispatchers.IO) {
        return@withContext api.getCart()
    }

    suspend fun updateCart(request:AddToCartItem) = withContext(Dispatchers.IO){
        return@withContext api.updateCart(request)
    }

    suspend fun removeCart(request:AddToCartItem) = withContext(Dispatchers.IO){
        return@withContext api.updateCart(request)
    }

    // Database
    suspend fun getProductDetailsFromDb(id: Int) = withContext(Dispatchers.IO) {
        return@withContext db.productDao().getProduct(id)
    }
}

fun dataToProductEntity(data: Data): ProductEntity {
    return ProductEntity(
        productId = data.Id,
        productName = data.Name,
        productShortDescription = data.ShortDescription,
        productLongDescription = data.FullDescription,
        oldPrice = data.ProductPrice.Price,
        newPrice = data.ProductPrice.BasePricePAngVValue.toString(),
        stock = data.StockAvailability,
        productImage = data.PictureModels[0].ImageUrl
    )
}
