package com.example.nopshop.converter

import androidx.room.TypeConverter
import com.example.nopshop.model.products.AddToCart
import com.example.nopshop.model.products.Breadcrumb
import com.example.nopshop.model.products.CategoryBreadcrumb
import com.example.nopshop.model.products.CustomPropertiesXXX
import com.example.nopshop.model.products.DefaultPictureModel
import com.example.nopshop.model.products.GiftCard
import com.example.nopshop.model.products.PictureModel
import com.example.nopshop.model.products.ProductEstimateShipping
import com.example.nopshop.model.products.ProductPrice
import com.example.nopshop.model.products.ProductReviewOverview
import com.example.nopshop.model.products.ProductSpecificationModel
import com.example.nopshop.model.products.VendorModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductDetailsConverter {
    @TypeConverter
    fun fromCustomPropertiesXXX(value: CustomPropertiesXXX): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCustomPropertiesXXX(value: String): CustomPropertiesXXX {
        return Gson().fromJson(value, CustomPropertiesXXX::class.java)
    }

    // Converters for List<PictureModel>
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

    // Converters for ProductPrice
    @TypeConverter
    fun fromProductPrice(value: ProductPrice): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProductPrice(value: String): ProductPrice {
        return Gson().fromJson(value, ProductPrice::class.java)
    }

    // Converters for ProductSpecificationModel
    @TypeConverter
    fun fromProductSpecificationModel(value: ProductSpecificationModel): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProductSpecificationModel(value: String): ProductSpecificationModel {
        return Gson().fromJson(value, ProductSpecificationModel::class.java)
    }

    // Converters for ProductReviewOverview
    @TypeConverter
    fun fromProductReviewOverview(value: ProductReviewOverview): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProductReviewOverview(value: String): ProductReviewOverview {
        return Gson().fromJson(value, ProductReviewOverview::class.java)
    }

    // Converters for ProductEstimateShipping
    @TypeConverter
    fun fromProductEstimateShipping(value: ProductEstimateShipping): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProductEstimateShipping(value: String): ProductEstimateShipping {
        return Gson().fromJson(value, ProductEstimateShipping::class.java)
    }

    // Converters for GiftCard
    @TypeConverter
    fun fromGiftCard(value: GiftCard): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGiftCard(value: String): GiftCard {
        return Gson().fromJson(value, GiftCard::class.java)
    }

    // Converters for DefaultPictureModel
    @TypeConverter
    fun fromDefaultPictureModel(value: DefaultPictureModel): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toDefaultPictureModel(value: String): DefaultPictureModel {
        return Gson().fromJson(value, DefaultPictureModel::class.java)
    }

    // Converters for List<CategoryBreadcrumb>
    @TypeConverter
    fun fromCategoryBreadcrumbList(value: List<CategoryBreadcrumb>): String {
        val type = object : TypeToken<List<CategoryBreadcrumb>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toCategoryBreadcrumbList(value: String): List<CategoryBreadcrumb> {
        val type = object : TypeToken<List<CategoryBreadcrumb>>() {}.type
        return Gson().fromJson(value, type)
    }

    // Converters for Breadcrumb
    @TypeConverter
    fun fromBreadcrumb(value: Breadcrumb): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toBreadcrumb(value: String): Breadcrumb {
        return Gson().fromJson(value, Breadcrumb::class.java)
    }

    // Converters for AddToCart
    @TypeConverter
    fun fromAddToCart(value: AddToCart): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAddToCart(value: String): AddToCart {
        return Gson().fromJson(value, AddToCart::class.java)
    }

    // Converters for VendorModel
    @TypeConverter
    fun fromVendorModel(value: VendorModel): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toVendorModel(value: String): VendorModel {
        return Gson().fromJson(value, VendorModel::class.java)
    }
}