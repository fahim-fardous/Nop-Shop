package com.example.nopshop.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.featureProducts.CustomProperties
import com.example.nopshop.model.featureProducts.PictureModel
import com.example.nopshop.model.featureProducts.ProductPrice
import com.example.nopshop.model.featureProducts.ProductSpecificationModel
import com.example.nopshop.model.featureProducts.ReviewOverviewModel
import com.example.nopshop.model.featureProducts.Data


class CustomProperties
data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomProperties,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val ThumbImageUrl: Any,
    val Title: String
)

data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: Any,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: Any,
    val OldPriceValue: Any,
    val PreOrderAvailabilityStartDateTimeUtc: Any,
    val Price: String,
    val PriceValue: Double
)

data class ProductSpecificationModel(
    val CustomProperties: CustomProperties,
)

data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)

@Entity(tableName = "feature_products")
data class FeatureProductsEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    val FullDescription: String,
    @PrimaryKey(autoGenerate = true)
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


