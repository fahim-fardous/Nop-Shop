package com.example.nopshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopshop.db.dbmodel.featureProduct.FeatureProductsEntity

@Dao
interface FeatureProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFeatureProducts(featureProducts: List<FeatureProductsEntity>)

    @Query("SELECT * FROM feature_products")
    suspend fun getFeatureProducts(): List<FeatureProductsEntity>



}