package com.example.nopshop.model.cart

data class OrderTotals(
    val CustomProperties: CustomPropertiesXX,
    val DisplayTax: Boolean,
    val DisplayTaxRates: Boolean,
    val GiftCards: List<Any>,
    val HideShippingTotal: Boolean,
    val IsEditable: Boolean,
    val OrderTotal: String,
    val OrderTotalDiscount: Any,
    val PaymentMethodAdditionalFee: Any,
    val RedeemedRewardPoints: Int,
    val RedeemedRewardPointsAmount: Any,
    val RequiresShipping: Boolean,
    val SelectedShippingMethod: Any,
    val Shipping: String,
    val SubTotal: String,
    val SubTotalDiscount: Any,
    val Tax: String,
    val TaxRates: List<Any>,
    val WillEarnRewardPoints: Int
)