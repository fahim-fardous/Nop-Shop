package com.example.nopshop.model.products

data class ProductReviewOverview(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomPropertiesXXX,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)