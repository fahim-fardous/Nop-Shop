package com.example.nopshop.model.category

data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: Any,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val PreOrderAvailabilityStartDateTimeUtc: Any,
    val Price: String,
    val PriceValue: Double
)