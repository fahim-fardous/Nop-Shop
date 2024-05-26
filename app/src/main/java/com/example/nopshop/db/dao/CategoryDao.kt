package com.example.nopshop.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopshop.db.dbmodel.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategory(category: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    suspend fun getAllCategory(): List<CategoryEntity>

}