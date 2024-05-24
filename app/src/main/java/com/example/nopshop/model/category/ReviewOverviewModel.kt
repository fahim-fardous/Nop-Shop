package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
):Parcelable