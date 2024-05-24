package com.example.nopshop.model.products

data class ProductPrice(
    val BasePricePAngV: Any,
    val BasePricePAngVValue: Double,
    val CallForPrice: Boolean,
    val CurrencyCode: String,
    val CustomProperties: CustomPropertiesXXX,
    val CustomerEntersPrice: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val HidePrices: Boolean,
    val IsRental: Boolean,
    val OldPrice: Any,
    val OldPriceValue: Any,
    val Price: String,
    val PriceValue: Double,
    val PriceWithDiscount: Any,
    val PriceWithDiscountValue: Any,
    val ProductId: Int,
    val RentalPrice: Any,
    val RentalPriceValue: Any
)