package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: @RawValue Any,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val OldPriceValue: @RawValue Any,
    val PreOrderAvailabilityStartDateTimeUtc: @RawValue Any,
    val Price: String,
    val PriceValue: Double
) : Parcelable