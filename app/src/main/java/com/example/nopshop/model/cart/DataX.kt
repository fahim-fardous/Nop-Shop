package com.example.nopshop.model.cart

data class DataX(
    val AnonymousPermissed: Boolean,
    val Cart: Cart,
    val EstimateShipping: EstimateShipping,
    val OrderTotals: OrderTotals,
    val SelectedCheckoutAttributes: String
)