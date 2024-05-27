package com.example.nopshop.repository

import com.example.nopshop.db.AppDatabase
import com.example.nopshop.db.dbmodel.product.ProductEntity
import com.example.nopshop.model.cart.AddToCartItem
import com.example.nopshop.model.cart.FormValue
import com.example.nopshop.model.products.Data
import com.example.nopshop.network.api.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository(
    private val db: AppDatabase,
    private val api: ProductApi
) {

    suspend fun getProductDetails(id: Int) = withContext(Dispatchers.IO) {
        val product = api.getProductDetails(id)

        val response = product.let {
            it.body()?.Data?.let { it1 -> dataToProductEntity(it1) }
        }
        db.productDao().saveProduct(response!!)
        return@withContext product
    }

    // Database
    suspend fun getProductDetailsFromDb(id: Int) = withContext(Dispatchers.IO) {
        return@withContext db.productDao().getProduct(id)
    }

    suspend fun addProductToCart(request:AddToCartItem, productId:Int) = withContext(Dispatchers.IO) {

        return@withContext api.addToCart(productId,request)
    }

}

fun dataToProductEntity(data: Data): ProductEntity {
    return ProductEntity(
        id = 0,
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
