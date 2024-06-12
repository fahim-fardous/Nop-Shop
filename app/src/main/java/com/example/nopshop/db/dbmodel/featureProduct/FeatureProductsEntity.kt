package com.example.nopshop.db.dbmodel.featureProduct

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.featureProducts.CustomProperties
import com.example.nopshop.model.featureProducts.PictureModel
import com.example.nopshop.model.featureProducts.ProductPrice
import com.example.nopshop.model.featureProducts.ProductSpecificationModel
import com.example.nopshop.model.featureProducts.ReviewOverviewModel
import com.example.nopshop.model.featureProducts.Data

@Entity(tableName = "feature_products")
data class FeatureProductsEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    val FullDescription: String,
    @PrimaryKey
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


fun FeatureProductsEntity.toFeatureData(): Data {
    return Data(
        CustomProperties = this.CustomProperties,
        FullDescription = FullDescription,
        Id = this.Id,
        MarkAsNew = this.MarkAsNew,
        Name = this.Name,
        PictureModels = this.PictureModels,
        ProductPrice = this.ProductPrice,
        ProductSpecificationModel = this.ProductSpecificationModel,
        ProductType = this.ProductType,
        ReviewOverviewModel = this.ReviewOverviewModel,
        SeName = this.SeName,
        ShortDescription = this.ShortDescription,
        Sku = this.Sku
    )
}


