package com.example.nopshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopshop.db.dbmodel.product.ProductEntity
import com.example.nopshop.model.products.Data

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductEntity)

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProduct(id: Int): ProductEntity
}