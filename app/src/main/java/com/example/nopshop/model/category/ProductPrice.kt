package com.example.nopshop.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val Price: String,
    val PriceValue: Double
):Parcelable