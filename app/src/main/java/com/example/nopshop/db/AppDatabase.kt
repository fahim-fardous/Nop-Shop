package com.example.nopshop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nopshop.converter.Converters
import com.example.nopshop.converter.FeatureProductsConverter
import com.example.nopshop.converter.OrderConverter
import com.example.nopshop.db.dao.CategoryDao
import com.example.nopshop.db.dao.FeatureProductsDao
import com.example.nopshop.db.dao.ProductDao
import com.example.nopshop.db.dao.SliderDao
import com.example.nopshop.db.dbmodel.category.CategoryEntity
import com.example.nopshop.db.dbmodel.featureProduct.FeatureProductsEntity
import com.example.nopshop.db.dbmodel.order.OrderEntity
import com.example.nopshop.db.dbmodel.product.ProductEntity
import com.example.nopshop.db.dbmodel.slider.SliderEntity

@Database(
    entities = [SliderEntity::class, CategoryEntity::class, FeatureProductsEntity::class, ProductEntity::class, OrderEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class, FeatureProductsConverter::class, OrderConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sliderDao(): SliderDao
    abstract fun categoryDao(): CategoryDao
    abstract fun featureProductDao(): FeatureProductsDao
    abstract fun productDao(): ProductDao

    companion object {
        operator fun invoke(context: Context) = buildDatabase(context)
        private fun buildDatabase(context: Context): AppDatabase {
            val databaseBuilder = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "AppDatabase.db"
            )
            return databaseBuilder.build()
        }
    }
}