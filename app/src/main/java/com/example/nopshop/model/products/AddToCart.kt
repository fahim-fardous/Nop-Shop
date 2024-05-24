package com.example.nopshop.model.products

data class AddToCart(
    val AllowedQuantities: List<Any>,
    val AvailableForPreOrder: Boolean,
    val CustomProperties: CustomPropertiesXXX,
    val CustomerEnteredPrice: Double,
    val CustomerEnteredPriceRange: Any,
    val CustomerEntersPrice: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val EnteredQuantity: Int,
    val IsRental: Boolean,
    val MinimumQuantityNotification: Any,
    val PreOrderAvailabilityStartDateTimeUserTime: Any,
    val PreOrderAvailabilityStartDateTimeUtc: Any,
    val ProductId: Int,
    val UpdateShoppingCartItemType: Any,
    val UpdatedShoppingCartItemId: Int
)