package com.example.nopshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopshop.db.dbmodel.order.OrderEntity
import com.example.nopshop.db.dbmodel.product.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductEntity)

    @Query("SELECT * FROM product WHERE productId = :id")
    suspend fun getProduct(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE email = :email")
    suspend fun getOrders(email: String): List<OrderEntity>
}