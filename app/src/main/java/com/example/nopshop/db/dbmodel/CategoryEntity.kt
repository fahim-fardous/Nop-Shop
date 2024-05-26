package com.example.nopshop.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopshop.model.category.CustomProperties
import com.example.nopshop.model.category.Data
import com.example.nopshop.model.category.PictureModel
import com.example.nopshop.model.category.Product
import com.example.nopshop.model.category.ProductPrice
import com.example.nopshop.model.category.ProductSpecificationModel
import com.example.nopshop.model.category.ReviewOverviewModel
import com.example.nopshop.model.category.SubCategory
import kotlinx.parcelize.RawValue

class CustomProperties
data class Product(
    val CustomProperties: CustomProperties,
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

data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomProperties,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val Title: String
)

data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: Any = Any(),
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val OldPriceValue: Any = Any(),
    val PreOrderAvailabilityStartDateTimeUtc: Any = Any(),
    val Price: String,
    val PriceValue: Double
)

data class ProductSpecificationModel(
    val CustomProperties: CustomProperties,
    val Groups: List<Any> = emptyList()
)

data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)


data class SubCategory(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products: List<Any> = emptyList(),
    val SeName: String,
    val SubCategories: List<Any> = emptyList()
)

@Entity(tableName = "category")
data class CategoryEntity(
    val CustomProperties: CustomProperties = CustomProperties(),
    @PrimaryKey
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory> = emptyList()
)

fun CategoryEntity.toData(): Data {
    return Data(
        CustomProperties = this.CustomProperties,
        Id = this.Id,
        Name = this.Name,
        Products = this.Products,
        SeName = this.SeName,
        SubCategories = this.SubCategories
    )
}



