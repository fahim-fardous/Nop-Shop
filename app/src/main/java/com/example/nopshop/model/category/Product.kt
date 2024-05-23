package com.example.nopshop.model.category

data class Product(
    val CustomProperties: CustomProperties,
    val DefaultPictureModel: DefaultPictureModel,
    val FullDescription: String,
    val Id: Int,
    val MarkAsNew: Boolean,
    val Name: String,
    val ProductPrice: ProductPrice,
    val ProductType: Int,
    val ReviewOverviewModel: ReviewOverviewModel,
    val SeName: String,
    val ShortDescription: String,
    val Sku: String,
    val SpecificationAttributeModels: List<Any>
)