package com.example.nopshop.model.featureProducts

import com.example.nopshop.db.dbmodel.featureProduct.FeatureProductsEntity

data class Data(
    val CustomProperties: com.example.nopshop.model.featureProducts.CustomProperties,
    val FullDescription: String,
    val Id: Int,
    val MarkAsNew: Boolean,
    val Name: String,
    val PictureModels: List<PictureModel>,
    val ProductPrice: ProductPrice,
    val ProductSpecificationModel: ProductSpecificationModel,
    val ProductType: Int,
    val ReviewOverviewModel: ReviewOverviewModel,
    val SeName: String,
    val ShortDescription: String,
    val Sku: String
)

fun Data.asEntity() = FeatureProductsEntity(
    CustomProperties = CustomProperties,
    FullDescription = FullDescription,
    Id = Id,
    MarkAsNew = MarkAsNew,
    Name = Name,
    PictureModels = PictureModels,
    ProductPrice = ProductPrice,
    ProductSpecificationModel = ProductSpecificationModel,
    ProductType = ProductType,
    ReviewOverviewModel = ReviewOverviewModel,
    SeName = SeName,
    ShortDescription = ShortDescription,
    Sku = Sku
)