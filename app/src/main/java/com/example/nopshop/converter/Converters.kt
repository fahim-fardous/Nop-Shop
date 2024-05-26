package com.example.nopshop.converter

import androidx.room.TypeConverter
import com.example.nopshop.model.category.CustomProperties
import com.example.nopshop.model.category.PictureModel
import com.example.nopshop.model.category.Product
import com.example.nopshop.model.category.ProductPrice
import com.example.nopshop.model.category.ProductSpecificationModel
import com.example.nopshop.model.category.ReviewOverviewModel
import com.example.nopshop.model.category.SubCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromProductList(value: List<Product>): String {
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductList(value: String): List<Product> {
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSubCategoryList(value: List<SubCategory>): String {
        val type = object : TypeToken<List<SubCategory>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toSubCategoryList(value: String): List<SubCategory> {
        val type = object : TypeToken<List<SubCategory>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromPictureModelList(value: List<PictureModel>): String {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toPictureModelList(value: String): List<PictureModel> {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductPriceList(value: List<ProductPrice>): String {
        val type = object : TypeToken<List<ProductPrice>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductPriceList(value: String): List<ProductPrice> {
        val type = object : TypeToken<List<ProductPrice>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductSpecificationModel(value: ProductSpecificationModel): String {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductSpecificationModel(value: String): ProductSpecificationModel {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromReviewOverviewModel(value: ReviewOverviewModel): String {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toReviewOverviewModel(value: String): ReviewOverviewModel {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCustomProperties(value: CustomProperties): String {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toCustomProperties(value: String): CustomProperties {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().fromJson(value, type)
    }
}